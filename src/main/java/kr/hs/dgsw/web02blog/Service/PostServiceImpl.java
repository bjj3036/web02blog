package kr.hs.dgsw.web02blog.Service;

import kr.hs.dgsw.web02blog.Domain.Post;
import kr.hs.dgsw.web02blog.Domain.User;
import kr.hs.dgsw.web02blog.Repository.PostRepository;
import kr.hs.dgsw.web02blog.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @Override
    public Post createPost(Post post) {
        Optional<User> foundUser = this.userRepository.findById(post.getUserId());
        if (!foundUser.isPresent())
            return null;
        return this.postRepository.save(post);
    }

    @Override
    public Post readPost(Long id) {
        return this.postRepository.findById(id).orElse(null);
    }

    @Override
    public List<Post> readAllPost() {
        return this.postRepository.findAll();
    }

    @Override
    public Post updatePost(Post post) {
        Post found = this.postRepository.findById(post.getId()).map(post1 -> {
            if (!post.getContent().isEmpty())
                post1.setContent(post.getContent());
            if (!post.getFileName().isEmpty())
                post1.setFileName(post.getFileName());
            if (!post.getFilePath().isEmpty())
                post1.setFilePath(post.getFilePath());
            return post1;
        }).orElse(null);
        if (found == null)
            return null;
        this.postRepository.save(found);
        return null;
    }

    @Override
    public boolean removePost(Long id) {
        try {
            this.postRepository.deleteById(id);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
