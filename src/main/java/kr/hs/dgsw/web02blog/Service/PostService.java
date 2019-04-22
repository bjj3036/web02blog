package kr.hs.dgsw.web02blog.Service;

import kr.hs.dgsw.web02blog.Domain.Post;

import java.util.List;

public interface PostService {
    Post createPost(Post post);

    Post readPost(Long id);

    List<Post> readAllPost();

    Post updatePost(Post post);

    boolean removePost(Long id);
}
