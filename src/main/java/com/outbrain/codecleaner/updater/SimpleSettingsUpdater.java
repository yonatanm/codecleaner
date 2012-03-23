package com.outbrain.codecleaner.updater;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * an updater that loads settings from source and copies it to the target
 * @author yonatan
 * @date Nov 24, 2011
 */
public class SimpleSettingsUpdater implements SettingsUpdater {

  protected final File sourceFile;
  protected final File targetFile;

  public SimpleSettingsUpdater(final File sourceFile, final File targetFile) {
    this.sourceFile = sourceFile;
    this.targetFile = targetFile;
  }

  @Override
  public void update() throws IOException {
    copy();
  }

  private void copy() throws IOException {
    final byte[] buffer = new byte[1024];
    final InputStream is = new FileInputStream(sourceFile);
    final FileOutputStream os = new FileOutputStream(targetFile);
    while (true) {
      final int len = is.read(buffer);
      if (len < 0) {
        break;
      }
      os.write(buffer, 0, len);
    }
    is.close();
    os.close();
  }

  @Override
  public String report() {
    return new StringBuilder().append(String.format("copied settings from %s to %s", sourceFile, targetFile))
                              .toString();
  }

  @Override
  public File getTarget() {
    return targetFile;
  }

}
