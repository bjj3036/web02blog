package kr.hs.dgsw.web02blog.Service;

import kr.hs.dgsw.web02blog.Domain.Attachment;
import kr.hs.dgsw.web02blog.Domain.Post;
import kr.hs.dgsw.web02blog.Domain.User;
import kr.hs.dgsw.web02blog.Protocol.PostUsernameProtocol;
import kr.hs.dgsw.web02blog.Repository.PostRepository;
import kr.hs.dgsw.web02blog.Repository.UserRepository;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @Override
    public PostUsernameProtocol createPost(Post post) throws Exception {
        Optional<User> foundUser = this.userRepository.findById(post.getUserId());
        if (!foundUser.isPresent())
            throw new Exception("Can not find User");
        return new PostUsernameProtocol(this.postRepository.save(post), foundUser.get().getName());
    }

    @Override
    public PostUsernameProtocol readPost(Long id) throws Exception {
        Optional<Post> found = this.postRepository.findById(id);
        if (!found.isPresent())
            throw new Exception("Can not find Post");
        return makeProtocol(found.get());
    }

    @Override
    public PostUsernameProtocol readPostByUserId(Long id) throws Exception {
        Optional<Post> found = this.postRepository.findTopByUserIdOrderByIdDesc(id);
        if (!found.isPresent())
            throw new Exception("Can not find Post");
        return makeProtocol(found.get());
    }

    @Override
    public List<PostUsernameProtocol> readAllPost() {
        List<PostUsernameProtocol> list = new ArrayList<>();
        for (Post post : this.postRepository.findAllByOrderByCreatedDesc()) {
            list.add(makeProtocol(post));
        }
        return list;
    }

    @Override
    public PostUsernameProtocol updatePost(Post post) throws Exception {
        Post found = this.postRepository.findById(post.getId()).map(post1 -> {
            if (!post.getContent().isEmpty())
                post1.setContent(post.getContent());
            return post1;
        }).orElse(null);
        if (found == null)
            throw new Exception("Can not find Post");
        return makeProtocol(this.postRepository.save(found));
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
    public Attachment uploadCommentImage(MultipartFile uploadFile) throws Exception {
        return null;
    }

    private PostUsernameProtocol makeProtocol(Post post) {
        Optional<User> foundUser = this.userRepository.findById(post.getUserId());
        return new PostUsernameProtocol(post, foundUser.get().getName());
    }
}
