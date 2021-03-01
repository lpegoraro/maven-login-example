package br.pegoraro.pegz.effectivedollop.access.repositories;

import br.pegoraro.pegz.effectivedollop.access.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
