package com.godeltech.bikesharing.service.email.impl;

import com.godeltech.bikesharing.models.EmailSendModel;
import com.godeltech.bikesharing.models.ReportModel;
import com.godeltech.bikesharing.models.enums.IncomeTimeUnit;
import com.godeltech.bikesharing.models.enums.ReportType;
import com.godeltech.bikesharing.service.admin.report.income.IncomeReportCreator;
import com.godeltech.bikesharing.service.email.EmailService;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
  private final JavaMailSender mailSender;
  private final IncomeReportCreator reportCreator;

  @Override
  public void sendEmail(EmailSendModel emailSendModel) {
    var type = emailSendModel.getReportType();
    var attachment = createReport(type);
    var preparedMessage = prepareMessage(emailSendModel, attachment);
    mailSender.send(preparedMessage);
  }

  private MimeMessage prepareMessage(EmailSendModel emailSendModel, ReportModel attachment) {
    MimeMessage message = mailSender.createMimeMessage();
    MimeMessageHelper mimeMessageHelper;
    try {
      mimeMessageHelper = new MimeMessageHelper(message, true);
      mimeMessageHelper.setTo(emailSendModel.getRecipient());
      mimeMessageHelper.setFrom(emailSendModel.getSender());
      mimeMessageHelper.setSubject(emailSendModel.getSubject());
      mimeMessageHelper.setText(emailSendModel.getMessage());
      setAttachment(mimeMessageHelper, attachment);
    } catch (MessagingException e) {
      e.printStackTrace();
    }

    return message;
  }

  private void setAttachment(MimeMessageHelper mimeMessageHelper, ReportModel attachment) {
    try {
      mimeMessageHelper.addAttachment(attachment.getFileName(),
          getInputStream(attachment.getData()),
          attachment.getContentType());
    } catch (MessagingException | IOException e) {
      e.printStackTrace();
    }
  }

  private InputStreamSource getInputStream(StreamingResponseBody data) throws IOException {
    ByteArrayOutputStream outStream = new ByteArrayOutputStream();
    data.writeTo(outStream);
    return () -> new ByteArrayInputStream(outStream.toByteArray());
  }

  private ReportModel createReport(ReportType type) {
    return reportCreator.createReport(type, IncomeTimeUnit.DAY, LocalDate.now());
  }
}
