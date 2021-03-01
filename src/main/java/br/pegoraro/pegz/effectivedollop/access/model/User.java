package br.pegoraro.pegz.effectivedollop.access.model;

import lombok.Data;
import org.hibernate.annotations.ManyToAny;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.EnumSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Data
@Entity
@Table(name = "user")
public class User implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private String id;

  private String username;

  private String password;

  @Transient
  private String passwordConfirm;

  @ManyToMany
  private Set<Role> authorities;

  @Transient
  private EnumSet<UserStatus> userStatuses;

  private String userStatus;

  @Override
  public boolean isAccountNonExpired() {
    return !userStatuses.contains(UserStatus.EXPIRED);
  }

  @Override
  public boolean isAccountNonLocked() {
    return !userStatuses.contains(UserStatus.LOCKED);
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return !userStatuses.contains(UserStatus.CREDS_EXPIRED);
  }

  @Override
  public boolean isEnabled() {
    return userStatuses.contains(UserStatus.ENABLED);
  }
}
