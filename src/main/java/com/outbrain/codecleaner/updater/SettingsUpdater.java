package com.outbrain.codecleaner.updater;

import java.io.File;
import java.io.IOException;

/**
 * an interface for settings updater
 * @author yonatan
 * @date Nov 26, 2011
 *
 */
public interface SettingsUpdater {

  /**
   * @throws IOException
   */
  void update() throws IOException;

  /**
   * @return
   */
  String report();

  public File getTarget();
}
