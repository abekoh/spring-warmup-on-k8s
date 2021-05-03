package dev.abekoh.warmup.controllers.webapi;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class WebApiUserAddRequest {

  private final String firstName;

  private final String lastName;

  private final Integer birthYear;

  private final Integer birthMonth;

  private final Integer birthDate;
}