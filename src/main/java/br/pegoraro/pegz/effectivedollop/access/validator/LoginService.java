package br.pegoraro.pegz.effectivedollop.access.validator;

import br.pegoraro.pegz.effectivedollop.access.model.Login;
import br.pegoraro.pegz.effectivedollop.access.model.LoginAttempt;
import br.pegoraro.pegz.effectivedollop.access.model.LoginStatus;
import br.pegoraro.pegz.effectivedollop.access.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class LoginService implements UserDetailsService {

  private Map<String, User> inMemoryUserDb;
  private LoginValidator loginValidator;

  public LoginService(LoginValidator loginValidator) {
    this.loginValidator = loginValidator;
    this.inMemoryUserDb = new HashMap<>();
  }

  @Override
  public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
    return inMemoryUserDb.get(login);
  }

  public LoginAttempt login(Login login) {
    if (inMemoryUserDb.containsKey(login.getUsername())) {
      return loginValidator.validate(login, inMemoryUserDb.get(login.getUsername()));
    } else {
      return LoginAttempt.of(LoginStatus.FORBIDDEN, login);
    }

  }


}
