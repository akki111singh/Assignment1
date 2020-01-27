package com.cleancoder.args;

import java.util.*;
import static com.cleancoder.args.ArgsException.ErrorCode.*;
import com.cleancoder.args.marshalers.*;

public class Args {
  private Map<Character, ArgumentMarshaler> marshalers;
  private Set<Character> argsFound;
  private ListIterator<String> currentArgument;

  public Args(String schema, String[] args) throws ArgsException {
    marshalers = new HashMap<Character, ArgumentMarshaler>();
    argsFound = new HashSet<Character>();

    parseSchema(schema);
    parseArgumentStrings(Arrays.asList(args));
    }

  private void parseSchema(String schema) throws ArgsException {
    for (String element : schema.split(","))
      if (element.length() > 0)
        parseSchemaElement(element.trim());
  }

  private void parseSchemaElement(String element) throws ArgsException {
    char elementId = element.charAt(0);
    String elementTail = element.substring(1);

    validateSchemaElementId(elementId);
    switch (elementTail) {
      case "":
        marshalers.put(elementId, new BooleanArgumentMarshaler());
        break;
      case "*":
        marshalers.put(elementId, new StringArgumentMarshaler());
        break;
      case "#":
        marshalers.put(elementId, new IntegerArgumentMarshaler());
        break;
      case "##":
        marshalers.put(elementId, new DoubleArgumentMarshaler());
        break;
      case "[*]":
        marshalers.put(elementId, new StringArrayArgumentMarshaler());
        break;
      case "&":
        marshalers.put(elementId, new MapArgumentMarshaler());
        break;
      default:
        throw new ArgsException(INVALID_ARGUMENT_FORMAT, elementId, elementTail);
    }
  }

  private void validateSchemaElementId(char elementId) throws ArgsException {
    if (!Character.isLetter(elementId))
      throw new ArgsException(INVALID_ARGUMENT_NAME, elementId, null);
  }

  private void parseArgumentStrings(List<String> argsList) throws ArgsException {
    for (currentArgument = argsList.listIterator(); currentArgument.hasNext();) {
      String argString = currentArgument.next();
      if (argString.startsWith("-")) {
        parseArgumentCharacters(argString.substring(1));
      } else {
        currentArgument.previous();
        break;
      }
    }
  }

  private void parseArgumentCharacters(String argChars) throws ArgsException {
    char[] argCharsCharArray = argChars.toCharArray();
    for (char argChar:argCharsCharArray)
      parseArgumentCharacter(argChar);
  }

  private void parseArgumentCharacter(char argChar) throws ArgsException {
    ArgumentMarshaler m = marshalers.get(argChar);
    Optional<ArgumentMarshaler> checkNull = Optional.ofNullable(marshalers.get(argChar));
    if (checkNull.isPresent()) {
      argsFound.add(argChar);
      try {
        m.set(currentArgument);
      } catch (ArgsException e) {
        e.setErrorArgumentId(argChar);
        throw e;
      }
    } else {
      throw new ArgsException(UNEXPECTED_ARGUMENT, argChar, null);
  }
}

  public boolean has(char arg) {
    return argsFound.contains(arg);
  }

  public int nextArgument() {
    return currentArgument.nextIndex();
  }

  public boolean getBoolean(char arg) {
    return BooleanArgumentMarshaler.getValue(marshalers.get(arg));
  }

  public String getString(char arg) {
    return StringArgumentMarshaler.getValue(marshalers.get(arg));
  }

  public int getInt(char arg) {
    return IntegerArgumentMarshaler.getValue(marshalers.get(arg));
  }

  public double getDouble(char arg) {
    return DoubleArgumentMarshaler.getValue(marshalers.get(arg));
  }

  public String[] getStringArray(char arg) {
    return StringArrayArgumentMarshaler.getValue(marshalers.get(arg));
  }

  public Map<String, String> getMap(char arg) {
    return MapArgumentMarshaler.getValue(marshalers.get(arg));
  }
}
