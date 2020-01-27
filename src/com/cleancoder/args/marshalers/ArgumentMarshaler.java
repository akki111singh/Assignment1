package com.cleancoder.args.marshalers;

import java.util.Iterator;
import com.cleancoder.args.ArgsException;

public interface ArgumentMarshaler {
  void set(Iterator<String> currentArgument) throws ArgsException;
}
