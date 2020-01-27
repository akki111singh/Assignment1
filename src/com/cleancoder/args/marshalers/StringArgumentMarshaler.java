package com.cleancoder.args.marshalers;

import java.util.Iterator;
import java.util.NoSuchElementException;

import com.cleancoder.args.ArgsException;

import static com.cleancoder.args.ArgsException.ErrorCode.MISSING_STRING;

public class StringArgumentMarshaler implements ArgumentMarshaler {
  private String stringValue = "";

  public void set(Iterator<String> currentArgument) throws ArgsException {
    try {
      stringValue = currentArgument.next();
    } catch (NoSuchElementException e) {
      throw new ArgsException(MISSING_STRING);
    }
  }

  public static boolean Validated(ArgumentMarshaler am)  {
    if (am != null && am instanceof StringArgumentMarshaler)  {
      return true;
    }
    return false;
  }

  public static String getValue(ArgumentMarshaler am) {
    if (Validated(am))
      return ((StringArgumentMarshaler) am).stringValue;
    else
      return "";
  }
}
