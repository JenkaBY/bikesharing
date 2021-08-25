package com.godeltech.bikesharing.service.admin.report.xlsx;

import java.io.File;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@Slf4j
public abstract class XlsxReport {
  protected Workbook workbook;
  private static final String FILE_FORMAT = "xlsx";
  private final String fileName;

  public XlsxReport(String fileName) {
    this.fileName = fileName;
    workbook = new XSSFWorkbook();
  }

  public final File generate() throws Exception {
    log.info("generate XLS-file");
    processSheets();
    return generateFile();
  }

  protected abstract void processSheets();

  private File generateFile() throws Exception {
    String pathToFile = TempFilePath.builder()
        .fileFormat(FILE_FORMAT)
        .fileName(fileName)
        .build().getFullPath();
    try (WorkbookFileWriter fileWriter = new WorkbookFileWriter(pathToFile)) {
      return fileWriter.write(workbook);
    }
  }

}