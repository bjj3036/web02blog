package kr.hs.dgsw.web02blog.Repository;

import kr.hs.dgsw.web02blog.Domain.Post;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findTopByUserIdOrderByIdDesc(Long userId);
    List<Post> findAllByOrderByCreatedDesc();
}
