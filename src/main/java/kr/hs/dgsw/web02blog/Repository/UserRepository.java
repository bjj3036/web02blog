package kr.hs.dgsw.web02blog.Repository;

import kr.hs.dgsw.web02blog.Domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByAccount(String account);
}
