package br.pegoraro.pegz.effectivedollop.access;

import br.pegoraro.pegz.effectivedollop.access.model.Login;
import br.pegoraro.pegz.effectivedollop.access.model.LoginAttempt;
import br.pegoraro.pegz.effectivedollop.access.validator.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

  private LoginService loginService;

  @RequestMapping("/auth")
  public LoginAttempt onSubmit(Login login) {
    return loginService.login(login);
  }

}
