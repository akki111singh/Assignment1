package com.cleancoder.args.marshalers;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

import com.cleancoder.args.ArgsException;
import static com.cleancoder.args.ArgsException.ErrorCode.*;

public class MapArgumentMarshaler implements ArgumentMarshaler {
  private Map<String, String> map = new HashMap<>();

  public void set(Iterator<String> currentArgument) throws ArgsException {
    try {
      String[] mapEntries = currentArgument.next().split(",");
      for (String entry : mapEntries) {
        String[] entryComponents = entry.split(":");
        if (entryComponents.length != 2)
          throw new ArgsException(MALFORMED_MAP);
        map.put(entryComponents[0], entryComponents[1]);
      }
    } catch (NoSuchElementException e) {
      throw new ArgsException(MISSING_MAP);
    }
  }

  public static boolean Validated(ArgumentMarshaler am) {
    if (am != null && am instanceof MapArgumentMarshaler) {
      return true;
    }
      return false;
  }

  public static Map<String, String> getValue(ArgumentMarshaler am) {
    if (Validated(am))
      return ((MapArgumentMarshaler) am).map;
    else
      return new HashMap<>();
  }
}
