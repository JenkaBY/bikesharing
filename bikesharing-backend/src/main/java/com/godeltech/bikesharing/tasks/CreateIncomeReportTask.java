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
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.yml")
@Slf4j
@RequiredArgsConstructor
public class CreateIncomeReportTask {
  private final Environment env;
  private final EmailService service;

  @Scheduled(cron = "0 0 22 * * ?")
  public void sendIncomeReport() {
    log.info("Send income report for previous day");
    final String reportType = env.getProperty("spring.mail.report-type");
    final String sender = env.getProperty("spring.mail.sender");
    final String[] recipients = Objects.requireNonNull(env.getProperty("spring.mail.recipients"))
        .split(",");
    var rType = StringToEnumConverter.convert(reportType, ReportType.class);
    var eMail = prepareEmails(sender, recipients, rType);
    eMail.forEach(service::sendEmail);
  }

  private List<EmailSendModel> prepareEmails(String sender, String[] recipients, ReportType rType) {
    return Arrays.stream(recipients)
        .map(r -> EmailSendModel.builder()
            .recipient((r))
            .sender(sender)
            .subject("IncomeReport")
            .message("Income report for: " + LocalDate.now())
            .reportType(rType)
            .build()).collect(Collectors.toList());
  }
}
