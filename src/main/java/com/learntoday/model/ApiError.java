package com.learntoday.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ApiError {
  private String type;
  private String message;
  private LocalDateTime time;
}
