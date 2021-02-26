package br.pegoraro.pegz.effectivedollop.access.validator;

import br.pegoraro.pegz.effectivedollop.access.model.Login;
import br.pegoraro.pegz.effectivedollop.access.model.LoginAttempt;
import br.pegoraro.pegz.effectivedollop.access.model.LoginStatus;
import br.pegoraro.pegz.effectivedollop.access.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Slf4j
@Service
public class LoginValidator {

  private MessageDigest messageDigest;

  public LoginValidator() {
    try {
      this.messageDigest = MessageDigest.getInstance("SHA-256");
    } catch (NoSuchAlgorithmException e) {
      log.error("Error on creating message digest");
      throw new InstantiationError("Error on creating message digest");
    }
  }

  public LoginAttempt validate(Login login, User user) {
    messageDigest.update(login.getPassword().getBytes());
    String hashedPassword = new String(messageDigest.digest());
    if (user.getPassword().hashCode() == hashedPassword.hashCode()) {
      return LoginAttempt.of(LoginStatus.SUCCESS, login);
    } else {
      return  LoginAttempt.of(LoginStatus.FORBIDDEN, login);
    }
  }
}
