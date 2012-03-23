package com.outbrain.codecleaner.goal;

import java.util.ArrayList;
import java.util.List;

import com.outbrain.codecleaner.core.ITask;

/**
 * Maven plugin goal to update all eclipse settings: compiler, validation, style findbugs and pmd
 *
 * @goal all
 * 
 * @phase validate
 */
public class AllSettingsMojo extends AbstractSettingsMojo {

  /**
   * @parameter  default-value="devtools/eclipse/settings/org.eclipse.wst.validation.prefs"
   */
  protected String validationPrefsFile;

  /**
   * @parameter  default-value="devtools/eclipse/settings/org.eclipse.wst.html.core.prefs"
   */
  protected String htmlValidationPrefsFile;

  /**
   * @parameter  default-value="devtools/eclipse/settings/org.eclipse.wst.xml.core.prefs"
   */
  protected String xmlValidationPrefsFile;
  /**
   * @parameter  default-value="devtools/eclipse/settings/org.eclipse.jst.jsp.core.prefs"
   */
  protected String jspValidationPrefsFile;
  /**
   * @parameter  default-value="devtools/eclipse/settings/org.eclipse.jdt.core.prefs"
   */
  protected String corePrefsFile;
  /**
   * @parameter  default-value="devtools/eclipse/settings/.fbprefs"
   */
  protected String findbugsFile;
  /**
   * @parameter  default-value="devtools/eclipse/settings/.pmd"
   */
  protected String pmdFileName;

  /**
   * @parameter  default-value="devtools/eclipse/settings/.ruleset"
   */
  protected String pmdRuleFileName;

  /**
   * @parameter  default-value="devtools/eclipse/settings/org.eclipse.jdt.ui.prefs"
   */
  protected String uiPrefsFile;

  /**
   * @return
   * 
   */
  @Override
  protected List<ITask> createTasks() {
    final ArrayList<ITask> $ = new ArrayList<ITask>();
    $.addAll(new CompilerSettingsMojo(parentBaseDir, projectBaseDir, corePrefsFile).createTasks());
    $.addAll(new FindBugsStyleSettingsMojo(parentBaseDir, projectBaseDir, findbugsFile).createTasks());
    $.addAll(new PmdStyleSettingsMojo(parentBaseDir, projectBaseDir, pmdFileName, pmdRuleFileName).createTasks());
    $.addAll(new StyleSettingsMojo(parentBaseDir, projectBaseDir, corePrefsFile, uiPrefsFile).createTasks());
    $.addAll(new ValidationSettingsMojo(parentBaseDir, projectBaseDir, validationPrefsFile, htmlValidationPrefsFile, xmlValidationPrefsFile, jspValidationPrefsFile).createTasks());
    return $;
  }

}
