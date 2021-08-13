package com.godeltech.bikesharing.utils;

import com.godeltech.bikesharing.models.enums.ReportType;

public class EmailUtils {

  public static EmailSendRequest getEmailSendReportRequest(ReportType type) {
    var emailSendRequest = new EmailSendRequest();
    emailSendRequest.setSender("report.creator@test.com");
    emailSendRequest.setRecipient("some.reciever@test.com");
    emailSendRequest.setSubject("EveryDay Income report");
    emailSendRequest.setReportType(type);

    return emailSendRequest;
  }
}
