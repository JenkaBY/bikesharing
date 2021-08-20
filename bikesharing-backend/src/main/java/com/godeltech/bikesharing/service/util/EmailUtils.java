package com.godeltech.bikesharing.service.util;

import com.godeltech.bikesharing.models.EmailSendModel;
import com.godeltech.bikesharing.models.enums.ReportType;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EmailUtils {
  public static List<EmailSendModel> prepareEmails(String sender, String[] recipients, ReportType type) {
    return Arrays.stream(recipients)
        .map(r -> EmailSendModel.builder()
            .recipient((r))
            .sender(sender)
            .subject(type.name() + "-report")
            .message("report created for: " + LocalDate.now())
            .reportType(type)
            .build()).collect(Collectors.toList());
  }
}
