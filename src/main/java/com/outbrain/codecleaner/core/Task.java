package com.outbrain.codecleaner.core;

import com.outbrain.codecleaner.updater.SettingsUpdater;

/**
 * @author yonatan
 * @date Dec 26, 2011
 */
public class Task implements ITask {

  SettingsUpdater settingsUpdater;
  String prefix;
  private final String explain;

  public Task(final SettingsUpdater settingsUpdater, final String explain, final String prefix) {
    super();
    this.settingsUpdater = settingsUpdater;
    this.explain = explain;
    this.prefix = prefix;
  }

  public Task(final SettingsUpdater settingsUpdater, final String explain) {
    this(settingsUpdater, explain, null);
  }

  @Override
  public SettingsUpdater getSettingsUpdater() {
    return settingsUpdater;
  }

  @Override
  public String getPrefix() {
    return prefix;
  }

  @Override
  public String explain() {
    return explain;
  }

}
