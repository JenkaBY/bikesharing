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
  private String REPORT_TYPE;

  @Value("${spring.mail.sender}")
  private String SENDER;

  @Value("${spring.mail.recipients}")
  private String RECIPIENTS;

  @Scheduled(cron = "0 0 22 * * ?")
  public void sendIncomeReport() {
    log.info("Send income report for previous day");
    final String[] recipients = Objects.requireNonNull(RECIPIENTS).split(",");
    var type = StringToEnumConverter.convert(REPORT_TYPE, ReportType.class);
    var emails = prepareEmails(SENDER, recipients, type);
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
