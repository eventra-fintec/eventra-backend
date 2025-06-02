package pe.edu.upc.eventra.msvc_users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.eventra.msvc_users.model.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

}

