package com.godeltech.bikesharing.tasks;

import com.godeltech.bikesharing.models.EmailSendModel;
import com.godeltech.bikesharing.models.enums.ReportType;
import com.godeltech.bikesharing.service.email.EmailService;
import com.godeltech.bikesharing.service.util.StringToEnumConverter;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class CreateIncomeReportTask {
  private final EmailService service;

  @Value("${spring.mail.report-type}")
  private String reportType;

  @Value("${spring.mail.sender}")
  private String sender;

  @Value("${spring.mail.recipients}")
  private String recipients;

  @Scheduled(cron = "0 0 22 * * ?")
  public void sendIncomeReport() {
    log.info("Send income report for previous day");
    final String[] recipients = Objects.requireNonNull(this.recipients).split(",");
    var type = StringToEnumConverter.convert(reportType, ReportType.class);
    var emails = prepareEmails(sender, recipients, type);
    emails.forEach(service::sendEmail);
  }

  private List<EmailSendModel> prepareEmails(String sender, String[] recipients, ReportType type) {
    return Arrays.stream(recipients)
        .map(r -> EmailSendModel.builder()
            .recipient((r))
            .sender(sender)
            .subject("IncomeReport")
            .message("Income report for: " + LocalDate.now())
            .reportType(type)
            .build()).collect(Collectors.toList());
  }
}
