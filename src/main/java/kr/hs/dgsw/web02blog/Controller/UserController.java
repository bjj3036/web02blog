package kr.hs.dgsw.web02blog.Controller;

import kr.hs.dgsw.web02blog.Domain.Post;
import kr.hs.dgsw.web02blog.Domain.User;
import kr.hs.dgsw.web02blog.Protocol.ResponseFormat;
import kr.hs.dgsw.web02blog.Protocol.ResponseType;
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
    public ResponseFormat readById(@PathVariable Long id) {
        try {
            return new ResponseFormat(ResponseType.USER_GET, this.userService.readUser(id));
        } catch (Exception e) {
            return new ResponseFormat(ResponseType.FAIL, e.getMessage());
        }
    }

    @GetMapping("/user/readAll")
    public ResponseFormat readAll() {
        return new ResponseFormat(ResponseType.USER_GET, this.userService.readAllUser(), "모든");
    }

    @PostMapping("/user/create")
    public ResponseFormat createUser(@RequestBody User user) {
        try {
            return new ResponseFormat(ResponseType.USER_ADD, this.userService.createUser(user));
        } catch (Exception e) {
            return new ResponseFormat(ResponseType.FAIL, e.getMessage());
        }
    }

    @PutMapping("/user/update")
    public ResponseFormat updateUser(@RequestBody User user) {
        try {
            return new ResponseFormat(ResponseType.USER_UPDATE, this.userService.updateUser(user));
        } catch (Exception e) {
            return new ResponseFormat(ResponseType.FAIL, e.getMessage());

        }
    }

    @DeleteMapping("/user/remove/{id}")
    public ResponseFormat removeUser(@PathVariable Long id) {
        try {
            return new ResponseFormat(ResponseType.USER_DELETE, this.userService.removeUser(id));
        } catch (Exception e) {
            return new ResponseFormat(ResponseType.FAIL, e.getMessage());
        }
    }
}
