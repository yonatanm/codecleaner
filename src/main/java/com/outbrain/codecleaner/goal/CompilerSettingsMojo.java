package com.outbrain.codecleaner.goal;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.outbrain.codecleaner.core.ITask;
import com.outbrain.codecleaner.core.Task;
import com.outbrain.codecleaner.updater.PropertiesSettingsUpdater;

/**
 * Update the compiler settings, merging settings into project/org.eclipse.jdt.core.prefs file
 *
 * @goal compiler
 * 
 * @phase validate
 */
public class CompilerSettingsMojo extends AbstractSettingsMojo {

  /**
   * @parameter  default-value="devtools/eclipse/settings/org.eclipse.jdt.core.prefs"
   */
  protected String corePrefsFile;

  CompilerSettingsMojo() {
  }

  CompilerSettingsMojo(final File parentBaseDir, final File projectBaseDir, final String corePrefsFile) {
    super(parentBaseDir, projectBaseDir);
    this.corePrefsFile = corePrefsFile;
  }

  @Override
  protected List<ITask> createTasks() {
    final ArrayList<ITask> $ = new ArrayList<ITask>();
    $.add(new Task(new PropertiesSettingsUpdater(new File(parentBaseDir, corePrefsFile), new File(projectBaseDir,
        SETTINGS + ORG_ECLIPSE_JDT_CORE_PREFS), "^org\\.eclipse\\.jdt\\.core\\.compiler\\..*"),
        "update compiler project file " + ORG_ECLIPSE_JDT_CORE_PREFS + " using " + corePrefsFile));
    return $;
  }
}
