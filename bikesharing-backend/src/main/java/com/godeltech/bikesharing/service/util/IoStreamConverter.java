package com.godeltech.bikesharing.service.util;

import com.godeltech.bikesharing.exception.ReportCreationException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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

  public static InputStreamSource convertToInputStream(File file) throws IOException {
    return () -> new FileInputStream(file);
  }

  public static StreamingResponseBody convertFileToStream(File file) {
    try {
      InputStream inputStream = new FileInputStream(file);
      return outputStream -> {
        int numberOfBytesToWrite;
        byte[] data = new byte[1024];
        while ((numberOfBytesToWrite = inputStream.read(data, 0, data.length)) != -1) {
          outputStream.write(data, 0, numberOfBytesToWrite);
        }
        inputStream.close();
      };
    } catch (IOException e) {
      throw new ReportCreationException("report-file reading failed because: " + e.getMessage());
    }
  }
}
