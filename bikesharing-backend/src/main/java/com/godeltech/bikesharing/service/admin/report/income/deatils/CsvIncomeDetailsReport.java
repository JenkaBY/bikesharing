package com.godeltech.bikesharing.service.admin.report.income.deatils;

import com.godeltech.bikesharing.models.IncomeDetailsItemModel;
import com.godeltech.bikesharing.service.admin.report.xlsx.TempFilePath;
import com.godeltech.bikesharing.service.util.DateUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CsvIncomeDetailsReport {
  private static final String FILE_FORMAT = "csv";
  private static final String CSV_SEPARATOR = ",";
  private final String fileName;
  private final String content;
  private final List<IncomeReportHeader> headers;

  public CsvIncomeDetailsReport(String fileName, List<IncomeDetailsItemModel> incomeDetailsItems) {
    this.fileName = fileName;
    this.content = getContent(incomeDetailsItems);
    this.headers = IncomeReportHeader.getValues();
  }

  private String getContent(List<IncomeDetailsItemModel> incomeDetailsItems) {
    StringBuffer sb = new StringBuffer();
    createHeader(sb);
    createContent(incomeDetailsItems, sb);

    return sb.toString();
  }

  private void createHeader(StringBuffer sb) {
    var headerValues = headers.stream()
        .collect(Collectors.toMap(IncomeReportHeader::getOrder, IncomeReportHeader::getName));
    fillRaw(sb, headerValues);
  }

  private void createContent(List<IncomeDetailsItemModel> incomeDetailsItems, StringBuffer sb) {
    var totalAmount = 0L;
    for (var item : incomeDetailsItems) {
      fillRaw(sb, getRawValues(item));
      totalAmount += item.getIncomeAmount();
    }
    fillRaw(sb, getFooter(totalAmount));
  }

  private Map<Integer, String> getFooter(long totalAmount) {
    var rawValues = new HashMap<Integer, String>();
    rawValues.put(0, "");
    rawValues.put(1, "");
    rawValues.put(2, "ИТОГО:");
    rawValues.put(3, String.valueOf(totalAmount));
    return rawValues;
  }

  private Map<Integer, String> getRawValues(IncomeDetailsItemModel item) {
    var rawValues = new HashMap<Integer, String>();
    rawValues.put(0, DateUtils.getFormattedDate(item.getDate()));
    rawValues.put(1, item.getEquipmentGroupCode());
    rawValues.put(2, item.getEquipmentRegistrationNumber());
    rawValues.put(3, item.getIncomeAmount().toString());
    return rawValues;
  }

  private void fillRaw(StringBuffer sb, Map<Integer, String> values) {
    sb.append(values.get(0));
    for (int i = 1; i < values.size(); i++) {
      nextValueInRaw(sb, values.get(i));
    }
    sb.append(System.lineSeparator());
  }

  private void nextValueInRaw(StringBuffer sb, String s) {
    sb.append(CSV_SEPARATOR);
    sb.append(s);
  }

  public final File generate() throws Exception {
    log.info("generate CSV-file");
    return generateFile();
  }

  private File generateFile() throws Exception {
    String pathToFile = TempFilePath.builder()
        .fileFormat(FILE_FORMAT)
        .fileName(fileName)
        .build().getFullPath();
    File fileToWrite = new File(pathToFile);
    try (OutputStream outputStream = new FileOutputStream(fileToWrite)) {
      byte[] bytesArray = content.getBytes();
      outputStream.write(bytesArray);
      outputStream.flush();
    }
    return fileToWrite;
  }


}
