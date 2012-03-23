package com.outbrain.codecleaner.goal;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.outbrain.codecleaner.core.ITask;
import com.outbrain.codecleaner.core.Task;
import com.outbrain.codecleaner.updater.PropertiesSettingsUpdater;
import com.outbrain.codecleaner.updater.SimpleSettingsUpdater;

/**
 * Maven plugin goal to update the normal, html and xml validation settings
 *
 * @goal validation
 * 
 * @phase validate
 */
public class ValidationSettingsMojo extends AbstractSettingsMojo {
  protected static final String ORG_ECLIPSE_VALIDATION_PREFS = "org.eclipse.wst.validation.prefs";
  protected static final String ORG_ECLIPSE_HTML_VALIDATION_PREFS = "org.eclipse.wst.html.core.prefs";
  protected static final String ORG_ECLIPSE_XML_VALIDATION_PREFS = "org.eclipse.wst.xml.core.prefs";
  protected static final String ORG_ECLIPSE_JSP_VALIDATION_PREFS = "org.eclipse.jst.jsp.core.prefs";

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

  ValidationSettingsMojo() {
  }

  ValidationSettingsMojo(final File parentBaseDir, final File projectBaseDir, final String validationPrefsFile, final String htmlValidationPrefsFile,
      final String xmlValidationPrefsFile, final String jspValidationPrefsFile) {
    super(parentBaseDir, projectBaseDir);
    this.validationPrefsFile = validationPrefsFile;
    this.htmlValidationPrefsFile = htmlValidationPrefsFile;
    this.xmlValidationPrefsFile = xmlValidationPrefsFile;
    this.jspValidationPrefsFile = jspValidationPrefsFile;
  }

  /**
   * @return
   * 
   */
  @Override
  protected List<ITask> createTasks() {
    final ArrayList<ITask> $ = new ArrayList<ITask>();
    $.add(new Task(new SimpleSettingsUpdater(new File(parentBaseDir, validationPrefsFile), new File(projectBaseDir, SETTINGS + ORG_ECLIPSE_VALIDATION_PREFS)),
        "update validation project file " + ORG_ECLIPSE_JDT_CORE_PREFS + " using " + validationPrefsFile));

    $.add(new Task(new PropertiesSettingsUpdater(new File(parentBaseDir, jspValidationPrefsFile), new File(projectBaseDir, SETTINGS + ORG_ECLIPSE_JSP_VALIDATION_PREFS)),
        "update jsp validation project file " + ORG_ECLIPSE_JSP_VALIDATION_PREFS + " using " + jspValidationPrefsFile));

    $.add(new Task(new PropertiesSettingsUpdater(new File(parentBaseDir, htmlValidationPrefsFile), new File(projectBaseDir, SETTINGS + ORG_ECLIPSE_HTML_VALIDATION_PREFS)),
        "update html validation project file " + ORG_ECLIPSE_HTML_VALIDATION_PREFS + " using " + htmlValidationPrefsFile));
    $.add(new Task(new PropertiesSettingsUpdater(new File(parentBaseDir, xmlValidationPrefsFile), new File(projectBaseDir, SETTINGS + ORG_ECLIPSE_XML_VALIDATION_PREFS)),
        "update xml validation project file " + ORG_ECLIPSE_XML_VALIDATION_PREFS + " using " + xmlValidationPrefsFile));
    return $;
  }

}
