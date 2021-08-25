package com.godeltech.bikesharing.service.email.impl;

import com.godeltech.bikesharing.models.EmailSendModel;
import com.godeltech.bikesharing.models.ReportModel;
import com.godeltech.bikesharing.service.admin.report.ReportTypeResolver;
import com.godeltech.bikesharing.service.email.EmailService;
import com.godeltech.bikesharing.service.util.IoStreamConverter;
import java.io.IOException;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
  private final JavaMailSender mailSender;
  private final ReportTypeResolver reportTypeResolver;

  @Override
  public void sendEmail(EmailSendModel emailSendModel) {
    var preparedMessage = prepareMessage(emailSendModel);
    mailSender.send(preparedMessage);
  }

  private MimeMessage prepareMessage(EmailSendModel emailSendModel) {
    var type = emailSendModel.getReportType();
    var attachment = reportTypeResolver.getReport(type);
    MimeMessage message = mailSender.createMimeMessage();
    setMessageData(emailSendModel, attachment, message);
    return message;
  }

  private void setMessageData(EmailSendModel emailSendModel, ReportModel attachment, MimeMessage message) {
    try {
      var mimeMessageHelper = new MimeMessageHelper(message, true);
      mimeMessageHelper.setTo(emailSendModel.getRecipient());
      mimeMessageHelper.setFrom(emailSendModel.getSender());
      mimeMessageHelper.setSubject(emailSendModel.getSubject());
      mimeMessageHelper.setText(emailSendModel.getMessage());
      mimeMessageHelper.addAttachment(attachment.getFileName(),
          IoStreamConverter.convertToInputStream(attachment.getFileContent()),
          attachment.getContentType());
    } catch (MessagingException | IOException e) {
      e.printStackTrace();
    }
  }

}
