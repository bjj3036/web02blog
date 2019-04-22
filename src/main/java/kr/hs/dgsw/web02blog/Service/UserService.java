package kr.hs.dgsw.web02blog.Service;


import kr.hs.dgsw.web02blog.Domain.User;

import java.util.List;

public interface UserService {
    User createUser(User user);

    User readUser(Long id);

    User readUser(String account);

    List<User> readAllUser();

    User updateUser(User user);

    boolean removeUser(Long id);
}
