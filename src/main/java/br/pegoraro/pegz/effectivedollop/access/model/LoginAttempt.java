package br.pegoraro.pegz.effectivedollop.access.model;

import lombok.AllArgsConstructor;

import java.time.Instant;

@AllArgsConstructor
public class LoginAttempt {
  private Login login;
  private Instant instant;
  private LoginStatus loginStatus;
  public static LoginAttempt of(LoginStatus status, Login login) {
    return new LoginAttempt(login, Instant.now(), status);
  }
}
