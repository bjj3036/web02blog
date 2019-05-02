package kr.hs.dgsw.web02blog.Service;

import kr.hs.dgsw.web02blog.Domain.Attachment;
import kr.hs.dgsw.web02blog.Domain.Post;
import kr.hs.dgsw.web02blog.Protocol.PostUsernameProtocol;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface PostService {
    PostUsernameProtocol createPost(Post post) throws Exception;

    PostUsernameProtocol readPost(Long id) throws Exception;

    PostUsernameProtocol readPostByUserId(Long id) throws Exception;

    List<PostUsernameProtocol> readAllPost();

    PostUsernameProtocol updatePost(Post post) throws Exception;

    boolean removePost(Long id) throws Exception;

    void showCommentImage(Long id, HttpServletRequest req, HttpServletResponse res);

    Attachment uploadCommentImage(MultipartFile uploadFile) throws Exception;
}
