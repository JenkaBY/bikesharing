package com.godeltech.bikesharing.service.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.core.io.InputStreamSource;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

public class IoStreamConverter {

  public static StreamingResponseBody convertBookToStream(Workbook workbook) {
    return workbook::write;
  }

  public static StreamingResponseBody convertStringToStream(String string) {
    return outputStream -> outputStream.write(string.getBytes(StandardCharsets.UTF_8));
  }

  public static InputStreamSource convertToInputStream(StreamingResponseBody data) throws IOException {
    ByteArrayOutputStream outStream = new ByteArrayOutputStream();
    data.writeTo(outStream);
    return () -> new ByteArrayInputStream(outStream.toByteArray());
  }
}
