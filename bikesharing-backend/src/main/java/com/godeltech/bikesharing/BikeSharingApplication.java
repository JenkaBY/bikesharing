package com.godeltech.bikesharing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class BikeSharingApplication extends SpringBootServletInitializer {

  public static void main(String[] args) {
    SpringApplication.run(BikeSharingApplication.class, args);
  }
}