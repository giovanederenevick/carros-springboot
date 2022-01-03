package br.com.giovanederenevick.cars.repositories;

import br.com.giovanederenevick.cars.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByLogin(String login);
}
