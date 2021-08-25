package com.godeltech.bikesharing.service.admin.report.xlsx;

import java.io.File;
import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder
public class TempFilePath {
  private final String fileFormat;
  private final String fileName;
  private static final String PATH_SEPARATOR = File.separator;

  public String getFullPath() {
    return String.format("%s%s.%s", getTempDirectory(), fileName, fileFormat);
  }

  private String getTempDirectory() {
    String tempDir = System.getProperty("java.io.tmpdir");
    return tempDir.endsWith(PATH_SEPARATOR) ? tempDir : tempDir + PATH_SEPARATOR;
  }
}
