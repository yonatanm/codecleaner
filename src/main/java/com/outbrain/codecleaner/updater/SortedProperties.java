package com.outbrain.codecleaner.updater;

import java.util.Collections;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

/**
 * Properties that stored sorted (by key)
 * @author yonatan
 * @date Nov 24, 2011
 *
 */
class SortedProperties extends Properties {
  private static final long serialVersionUID = -809614024397612366L;

  @SuppressWarnings({ "rawtypes", "unchecked" })
  @Override
  public synchronized Enumeration keys() {
    final Enumeration keysEnum = super.keys();
    final Vector<String> keyList = new Vector<String>();
    while (keysEnum.hasMoreElements()) {
      keyList.add((String) keysEnum.nextElement());
    }
    Collections.sort(keyList);
    return keyList.elements();
  }
}