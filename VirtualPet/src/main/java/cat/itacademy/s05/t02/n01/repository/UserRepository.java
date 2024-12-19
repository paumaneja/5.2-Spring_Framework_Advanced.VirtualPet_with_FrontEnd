package cat.itacademy.s05.t02.n01.repository;

import cat.itacademy.s05.t02.n01.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}
