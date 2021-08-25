package com.godeltech.bikesharing.service.admin.report.xlsx;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;

@Slf4j
public class WorkbookFileWriter implements AutoCloseable {

  private final File fileToWrite;
  private final OutputStream outputStream;
  private final String pathToFile;

  public WorkbookFileWriter(String pathToFile) throws FileNotFoundException {
    this.pathToFile = pathToFile;
    fileToWrite = new File(pathToFile);
    outputStream = new FileOutputStream(fileToWrite);
  }

  public File write(Workbook workbook) throws IOException {
    log.info("Start writing workbook to file [{}]", pathToFile);
    workbook.write(outputStream);
    log.info("Finish writing workbook to file [{}]", pathToFile);
    return fileToWrite;
  }

  @Override
  public void close() throws Exception {
    outputStream.close();
  }
}