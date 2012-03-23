package com.outbrain.codecleaner.goal;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.outbrain.codecleaner.core.ITask;
import com.outbrain.codecleaner.core.Task;
import com.outbrain.codecleaner.updater.PropertiesSettingsUpdater;

/**
 * Maven plugin goal to update the eclipse formatter / cleanup / saveaction settings
 *
 * @goal style
 * 
 * @phase validate
 */
public class StyleSettingsMojo extends AbstractSettingsMojo {

  private static final String[] CORE_PREFS_INCLUDES = new String[] { "^org\\.eclipse\\.jdt\\.core\\.formatter\\..*", };
  private static final String[] UI_PREFS_CLEANUP_INCLUDES = new String[] { "^cleanup\\..*" };
  private static final String[] UI_PREFS_SAVE_ACTION_INCLUDES = new String[] {
                                                                              "^sp_cleanup\\..*",
                                                                              "^cleanup_profile$",
                                                                              "^cleanup_settings_version$",
                                                                              "^eclipse\\.preferences\\.version$",
                                                                              "^editor_save_participant_org.eclipse.jdt.ui.postsavelistener.cleanup$",
                                                                              "^formatter_profile$",
                                                                              "^formatter_settings_version$" };
  /**
   * @parameter  default-value="devtools/eclipse/settings/org.eclipse.jdt.core.prefs"
   */
  protected String corePrefsFile;

  /**
   * @parameter  default-value="devtools/eclipse/settings/org.eclipse.jdt.ui.prefs"
   */
  protected String uiPrefsFile;

  StyleSettingsMojo() {
  }

  StyleSettingsMojo(final File parentBaseDir, final File projectBaseDir, final String corePrefsFile,
      final String uiPrefsFile) {
    super(parentBaseDir, projectBaseDir);
    this.corePrefsFile = corePrefsFile;
    this.uiPrefsFile = uiPrefsFile;
  }

  @Override
  protected List<ITask> createTasks() {
    final ArrayList<ITask> $ = new ArrayList<ITask>();
    $.add(new Task(new PropertiesSettingsUpdater(new File(parentBaseDir, corePrefsFile), new File(projectBaseDir,
        SETTINGS + ORG_ECLIPSE_JDT_CORE_PREFS), CORE_PREFS_INCLUDES), "update formatter project file "
        + ORG_ECLIPSE_JDT_CORE_PREFS + " using " + corePrefsFile));
    $.add(new Task(new PropertiesSettingsUpdater(new File(parentBaseDir, uiPrefsFile), new File(projectBaseDir,
        SETTINGS + ORG_ECLIPSE_JDT_UI_PREFS), UI_PREFS_CLEANUP_INCLUDES), "update cleanUp project file "
        + ORG_ECLIPSE_JDT_UI_PREFS + " using " + uiPrefsFile));
    $.add(new Task(new PropertiesSettingsUpdater(new File(parentBaseDir, uiPrefsFile), new File(projectBaseDir,
        SETTINGS + ORG_ECLIPSE_JDT_UI_PREFS), UI_PREFS_SAVE_ACTION_INCLUDES), "update saveAction project file "
        + ORG_ECLIPSE_JDT_UI_PREFS + " using " + uiPrefsFile));

    return $;
  }
}