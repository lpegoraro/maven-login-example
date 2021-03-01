package br.pegoraro.pegz.effectivedollop.access.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;

@Entity

public class Role implements GrantedAuthority {

  private Roles role;

  @Override
  public String getAuthority() {
    return role.name();
  }
}
