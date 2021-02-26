package br.pegoraro.pegz.effectivedollop.access.model;

import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;

@Data
public class User implements UserDetails {
  private Set<Role> authorities;
  private String username;
  private String password;
  private Set<UserStatus> status;

  @Override
  public boolean isAccountNonExpired() {
    return !status.contains(UserStatus.EXPIRED);
  }

  @Override
  public boolean isAccountNonLocked() {
    return !status.contains(UserStatus.LOCKED);
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return !status.contains(UserStatus.CREDS_EXPIRED);
  }

  @Override
  public boolean isEnabled() {
    return status.contains(UserStatus.ENABLED);
  }
}
