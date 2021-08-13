package com.godeltech.bikesharing.service.email;

import com.godeltech.bikesharing.models.EmailSendModel;

public interface EmailService {
  void sendEmail(EmailSendModel emailSendRequest);
}
