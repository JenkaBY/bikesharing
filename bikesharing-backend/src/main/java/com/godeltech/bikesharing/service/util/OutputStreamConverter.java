package com.godeltech.bikesharing.service.util;

import java.nio.charset.StandardCharsets;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

public class OutputStreamConverter {

  public static StreamingResponseBody convertBookToStream(Workbook workbook) {
    return workbook::write;
  }

  public static StreamingResponseBody convertStringToStream(String string) {
    return outputStream -> outputStream.write(string.getBytes(StandardCharsets.UTF_8));
  }

}
