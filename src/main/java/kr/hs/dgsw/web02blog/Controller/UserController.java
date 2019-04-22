package kr.hs.dgsw.web02blog.Controller;

import kr.hs.dgsw.web02blog.Domain.Post;
import kr.hs.dgsw.web02blog.Domain.User;
import kr.hs.dgsw.web02blog.Service.PostService;
import kr.hs.dgsw.web02blog.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/user/readById/{id}")
    public User readById(@PathVariable Long id) {
        return this.userService.readUser(id);
    }

    @GetMapping("/user/readAll")
    public List<User> readAll() {
        return this.userService.readAllUser();
    }

    @PostMapping("/user/create")
    public User createUser(@RequestBody User user) {
        return this.userService.createUser(user);
    }

    @PutMapping("/user/update")
    public User updateUser(@RequestBody User user) {
        return this.userService.updateUser(user);
    }

    @DeleteMapping("/user/remove/{id}")
    public boolean removeUser(@PathVariable Long id) {
        return this.userService.removeUser(id);
    }
}
