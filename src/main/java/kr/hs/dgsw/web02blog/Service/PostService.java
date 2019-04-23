package kr.hs.dgsw.web02blog.Service;

import kr.hs.dgsw.web02blog.Domain.Attachment;
import kr.hs.dgsw.web02blog.Domain.Post;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface PostService {
    Post createPost(Post post) throws Exception;

    Post readPost(Long id) throws Exception;

    Post readPostByUserId(Long id) throws Exception;

    List<Post> readAllPost();

    Post updatePost(Post post) throws Exception;

    boolean removePost(Long id) throws Exception;

    void showCommentImage(Long id, HttpServletRequest req, HttpServletResponse res);

    Attachment uploadCommentImage(MultipartFile uploadFile) throws Exception;
}
