package com.outbrain.codecleaner.goal;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.outbrain.codecleaner.core.ITask;
import com.outbrain.codecleaner.core.Task;
import com.outbrain.codecleaner.updater.PropertiesSettingsUpdater;

/**
 * Maven plugin goal to update the findbugs settings
 *
 * @goal findbugs
 * 
 * @phase validate
 */
public class FindBugsStyleSettingsMojo extends AbstractSettingsMojo {
  private static final String FIND_BUGS_FILE_NAME = ".fbprefs";
  /**
   * @parameter  default-value="devtools/eclipse/settings/.fbprefs"
   */
  protected String findbugsFile;

  FindBugsStyleSettingsMojo() {
  }

  FindBugsStyleSettingsMojo(final File parentBaseDir, final File projectBaseDir, final String findbugsFile) {
    super(parentBaseDir, projectBaseDir);
    this.findbugsFile = findbugsFile;
  }

  @Override
  protected List<ITask> createTasks() {
    final ArrayList<ITask> $ = new ArrayList<ITask>();
    $.add(new Task(new PropertiesSettingsUpdater(new File(parentBaseDir, findbugsFile), new File(projectBaseDir,
        FIND_BUGS_FILE_NAME)), "update findBugs project file " + ORG_ECLIPSE_JDT_CORE_PREFS + " using " + findbugsFile));
    return $;
  }
}