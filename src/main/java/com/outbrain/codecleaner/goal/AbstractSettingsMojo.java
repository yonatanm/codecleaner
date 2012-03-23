package com.outbrain.codecleaner.goal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

import com.outbrain.codecleaner.core.ITask;

/**
 * an abstract for settings maven plugin. load project location and parent basedir
 * @author yonatan
 * @date Nov 27, 2011
 */
public abstract class AbstractSettingsMojo extends AbstractMojo {
  protected static final String SETTINGS = ".settings/";
  protected static final String ORG_ECLIPSE_JDT_CORE_PREFS = "org.eclipse.jdt.core.prefs";
  protected static final String ORG_ECLIPSE_JDT_UI_PREFS = "org.eclipse.jdt.ui.prefs";
  /**
   * @parameter expression="${project.parent.basedir}"
   */
  protected File parentBaseDir;

  /**
   * Location of the file.
   * @parameter expression="${project.basedir}"
   * @required
   */
  protected File projectBaseDir;

  public AbstractSettingsMojo() {

  }

  public AbstractSettingsMojo(final File parentBaseDir, final File projectBaseDir) {
    this.parentBaseDir = parentBaseDir;
    this.projectBaseDir = projectBaseDir;
  }

  @Override
  public void execute() throws MojoExecutionException {
    if (parentBaseDir == null)

    {
      getLog().info("skip main pom file");
      return;
    }
    getLog().info("parentBaseDir=" + parentBaseDir.getAbsolutePath());
    getLog().info("projectBaseDir=" + projectBaseDir.getAbsolutePath());
    if (!checkEclipse()) {
      getLog().info("missing .project,skip this project" + projectBaseDir.getAbsolutePath());
      return;
    }
    final List<ITask> tasks = createTasks();
    for (final ITask task : tasks) {
      prepare();
      try {
        handleTask(task);

      } catch (final FileNotFoundException e) {
        throw new MojoExecutionException(e.getMessage(), e);
      } catch (final IOException e) {
        throw new MojoExecutionException(e.getMessage(), e);
      }
    }
  }

  /**
   * @param task
   * @throws IOException
   */
  private void handleTask(final ITask task) throws IOException {
    saveOrig(task);
    explainTask(task);
    invokeTask(task);
    reportTask(task);
  }

  private void saveOrig(final ITask task) throws FileNotFoundException, IOException {
    final File targetFile = task.getSettingsUpdater().getTarget();
    if (targetFile.exists()) {
      final String origFileName = targetFile.getAbsolutePath() + ".orig";
      final File origFile = new File(origFileName);
      if (origFile.exists()) {
        return;
      }
      final FileOutputStream origStream = new FileOutputStream(origFile);
      IOUtils.copy(new FileInputStream(targetFile), origStream);
      origStream.close();
      getLog().info("saveing orig file " + origFileName);
    }

  }

  private void invokeTask(final ITask task) throws IOException {
    task.getSettingsUpdater().update();
  }

  /**
   * @param task
   */
  private void explainTask(final ITask task) {
    getLog().info(task.explain());
  }

  protected abstract List<ITask> createTasks();

  /**
   * creats .settings folder if missing
   */
  private void prepare() {
    final File settingsFile = new File(projectBaseDir.getAbsolutePath(), SETTINGS);
    if (!settingsFile.exists()) {
      getLog().info("missing .settings folder. creating it");
      if (!settingsFile.mkdirs()) {
        getLog().error("could not create " + settingsFile.getAbsolutePath() + " dir");
      }
    }
  }

  /**
   * checks if has .project
   */
  private boolean checkEclipse() {
    final File projectFile = new File(projectBaseDir.getAbsolutePath(), ".project");
    return projectFile.exists();
  }

  private void reportTask(final ITask task) {
    getLog().info(task.getSettingsUpdater().report());
  }

}
