package kr.hs.dgsw.web02blog.Service;


import kr.hs.dgsw.web02blog.Domain.Attachment;
import kr.hs.dgsw.web02blog.Domain.User;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface UserService {
    User createUser(User user) throws Exception;

    User readUser(Long id) throws Exception;

    User readUser(String account) throws Exception;

    List<User> readAllUser();

    User updateUser(User user) throws Exception;

    boolean removeUser(Long id) throws Exception;

    void showProfile(Long id, HttpServletRequest req, HttpServletResponse res);

    Attachment uploadProfile(Long id, MultipartFile uploadFile) throws Exception;
}
