package com.outbrain.codecleaner.updater;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Pattern;

/**
 * settings updater that load a prop file and merger it into exiting prop file
 * @author yonatan
 * @date Nov 24, 2011
 */
public class PropertiesSettingsUpdater implements SettingsUpdater {

  private static final Pattern ANY = Pattern.compile(".*");
  protected final File sourceFile;
  protected final File targetFile;
  protected final Properties source;
  protected final Properties target;
  protected int updated;
  protected int created;
  private final Pattern[] includes;

  public int getUpdated() {
    return updated;
  }

  public int getCreated() {
    return created;
  }

  public PropertiesSettingsUpdater(final File sourceFile, final File targetFile) {
    this.sourceFile = sourceFile;
    this.targetFile = targetFile;
    includes = new Pattern[] { ANY };
    source = new SortedProperties();
    target = new SortedProperties();
  }

  public PropertiesSettingsUpdater(final File sourceFile, final File targetFile, final String include) {
    this.sourceFile = sourceFile;
    this.targetFile = targetFile;

    includes = new Pattern[] { Pattern.compile(include) };
    source = new SortedProperties();
    target = new SortedProperties();
  }

  public PropertiesSettingsUpdater(final File sourceFile, final File targetFile, final String[] includes) {
    this.sourceFile = sourceFile;
    this.targetFile = targetFile;
    this.includes = new Pattern[includes.length];
    for (int i = 0; i < includes.length; i++) {
      this.includes[i] = Pattern.compile(includes[i]);
    }
    source = new SortedProperties();
    target = new SortedProperties();
  }

  @Override
  public void update() throws IOException {
    loadSource();
    loadTarget();
    merge();
    write();
  }

  private void loadSource() throws IOException {
    final FileInputStream inStream = new FileInputStream(sourceFile);
    source.load(inStream);
    inStream.close();
  }

  private void loadTarget() throws IOException {
    try {
      final FileInputStream inStream = new FileInputStream(targetFile);
      target.load(inStream);
      inStream.close();
    } catch (final FileNotFoundException e) {
    }
  }

  private void merge() {
    for (final Object key : source.keySet()) {
      final String keyAsString = String.valueOf(key);
      if (shouldCopy(keyAsString)) {
        final String newValue = source.getProperty(keyAsString);
        final Object previous = target.setProperty(keyAsString, newValue);
        if (previous != null) {
          if (!newValue.equals(String.valueOf(previous))) {
            updated++;
          }
        } else {
          created++;
        }
      }
    }
  }

  private boolean shouldCopy(final String key) {
    for (final Pattern include : includes) {
      if (include.matcher(key).matches()) {
        return true;
      }
    }
    return false;
  }

  private void write() throws FileNotFoundException, IOException {
    final String comment = "updated by Outbrain's CodeCleaner maven plugin at " + new Date();
    final FileOutputStream out = new FileOutputStream(targetFile);
    target.store(out, comment);
    out.close();
  }

  @Override
  public String report() {
    return new StringBuffer().append(String.format("%d properties modified. %d properties added ", updated, created))
                             .toString();
  }

  @Override
  public File getTarget() {
    return targetFile;
  }

}
