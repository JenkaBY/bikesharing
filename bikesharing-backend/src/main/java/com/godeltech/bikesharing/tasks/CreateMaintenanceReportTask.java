package com.godeltech.bikesharing.tasks;

import com.godeltech.bikesharing.models.enums.ReportType;
import com.godeltech.bikesharing.service.email.EmailService;
import com.godeltech.bikesharing.service.util.EmailUtils;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class CreateMaintenanceReportTask {
  private final EmailService service;

  @Value("${spring.mail.sender}")
  private String sender;

  @Value("${spring.mail.maintenance-recipients}")
  private String recipients;

  @Scheduled(cron = "0 0 22 * * ?")
  public void sendMaintenanceReport() {
    log.info("Send required-maintenance report");
    final String[] recipients = Objects.requireNonNull(this.recipients).split(",");
    var type = ReportType.MAINTENANCE;
    var emails = EmailUtils.prepareEmails(sender, recipients, type);
    emails.forEach(service::sendEmail);
  }
}
