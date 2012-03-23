package com.outbrain.codecleaner.core;

import com.outbrain.codecleaner.updater.SettingsUpdater;

/**
 * @author yonatan
 * @date Dec 26, 2011
 */
public interface ITask {
  public SettingsUpdater getSettingsUpdater();

  public String getPrefix();

  public String explain();
}
