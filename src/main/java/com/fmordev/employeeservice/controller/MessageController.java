package com.fmordev.employeeservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class MessageController {

  @Value("${app.message}")
  private String message;

  @GetMapping("/users/message")
  public String getMessage() {
    return message;
  }
}
