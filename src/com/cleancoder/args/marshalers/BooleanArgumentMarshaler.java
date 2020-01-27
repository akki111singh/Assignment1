package com.cleancoder.args.marshalers;

import java.util.Iterator;
import com.cleancoder.args.ArgsException;

public class BooleanArgumentMarshaler implements ArgumentMarshaler{
  private boolean booleanValue = false;

  public void set(Iterator<String> currentArgument) throws ArgsException {
    booleanValue = true;
  }

  public static boolean Validated(ArgumentMarshaler am) {
    if (am != null && am instanceof BooleanArgumentMarshaler){
      return true;
    }
    return false;
  }

  public static boolean getValue(ArgumentMarshaler am) {
    if(Validated(am)) {
      return ((BooleanArgumentMarshaler) am).booleanValue;
    }
    return false;
  }
}
