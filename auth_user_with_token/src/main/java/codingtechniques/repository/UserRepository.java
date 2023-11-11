package codingtechniques.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import codingtechniques.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
          
	Optional<User> findUserByEmail (String email);
}
