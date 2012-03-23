package com.outbrain.codecleaner.goal;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.outbrain.codecleaner.core.ITask;
import com.outbrain.codecleaner.core.Task;
import com.outbrain.codecleaner.updater.SimpleSettingsUpdater;

/**
 * Maven plugin goal to update the pmd and the ruleset files 1
 *
 * @goal pmd
 * 
 * @phase validate
 */
public class PmdStyleSettingsMojo extends AbstractSettingsMojo {
  private static final String PMD_FILE_NAME = ".pmd";
  private static final String PMD_RUL_FILE_NAME = ".ruleset";

  /**
   * @parameter  default-value="devtools/eclipse/settings/.pmd"
   */
  protected String pmdFileName;

  /**
   * @parameter  default-value="devtools/eclipse/settings/.ruleset"
   */
  protected String pmdRuleFileName;

  PmdStyleSettingsMojo() {
  }

  PmdStyleSettingsMojo(final File parentBaseDir, final File projectBaseDir, final String pmdFileName,
      final String pmdRuleFileName) {
    super(parentBaseDir, projectBaseDir);
    this.pmdFileName = pmdFileName;
    this.pmdRuleFileName = pmdRuleFileName;
  }

  @Override
  protected List<ITask> createTasks() {
    final ArrayList<ITask> $ = new ArrayList<ITask>();
    $.add(new Task(new SimpleSettingsUpdater(new File(parentBaseDir, pmdFileName), new File(projectBaseDir,
        PMD_FILE_NAME)), "update pmd project file " + PMD_FILE_NAME + " using " + pmdFileName));
    $.add(new Task(new SimpleSettingsUpdater(new File(parentBaseDir, pmdRuleFileName), new File(projectBaseDir,
        PMD_RUL_FILE_NAME)), "update pmd ruleset project file " + PMD_RUL_FILE_NAME + " using " + pmdRuleFileName));
    return $;
  }
}