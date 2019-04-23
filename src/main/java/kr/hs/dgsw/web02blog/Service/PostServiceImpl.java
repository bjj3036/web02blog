package kr.hs.dgsw.web02blog.Service;

import kr.hs.dgsw.web02blog.Domain.Attachment;
import kr.hs.dgsw.web02blog.Domain.Post;
import kr.hs.dgsw.web02blog.Domain.User;
import kr.hs.dgsw.web02blog.Repository.PostRepository;
import kr.hs.dgsw.web02blog.Repository.UserRepository;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @Override
    public Post createPost(Post post) throws Exception {
        Optional<User> foundUser = this.userRepository.findById(post.getUserId());
        if (!foundUser.isPresent())
            throw new Exception("Can not find User");
        return this.postRepository.save(post);
    }

    @Override
    public Post readPost(Long id) throws Exception {
        Optional<Post> found = this.postRepository.findById(id);
        if (!found.isPresent())
            throw new Exception("Can not find Post");
        return found.get();
    }

    @Override
    public Post readPostByUserId(Long id) throws Exception {
        Optional<Post> found = this.postRepository.findTopByUserIdOrderByIdDesc(id);
        if (!found.isPresent())
            throw new Exception("Can not find Post");
        return found.get();
    }

    @Override
    public List<Post> readAllPost() {
        return this.postRepository.findAll();
    }

    @Override
    public Post updatePost(Post post) throws Exception {
        Post found = this.postRepository.findById(post.getId()).map(post1 -> {
            if (!post.getContent().isEmpty())
                post1.setContent(post.getContent());
            return post1;
        }).orElse(null);
        if (found == null)
            throw new Exception("Can not find Post");
        return this.postRepository.save(found);
    }

    @Override
    public boolean removePost(Long id) throws Exception {
        this.postRepository.deleteById(id);
        return true;
    }

    @Override
    public void showCommentImage(Long id, HttpServletRequest req, HttpServletResponse res) {

    }

    @Override
    public Attachment uploadCommentImage(MultipartFile uploadFile) throws Exception{
        return null;
    }
}
