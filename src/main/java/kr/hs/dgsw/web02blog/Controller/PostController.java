package kr.hs.dgsw.web02blog.Controller;

import kr.hs.dgsw.web02blog.Domain.Post;
import kr.hs.dgsw.web02blog.Protocol.ResponseFormat;
import kr.hs.dgsw.web02blog.Protocol.ResponseType;
import kr.hs.dgsw.web02blog.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/post/readById/{id}")
    public ResponseFormat readById(@PathVariable Long id) {
        try {
            return new ResponseFormat(ResponseType.POST_GET, this.postService.readPost(id));
        } catch (Exception e) {
            return new ResponseFormat(ResponseType.FAIL, e.getMessage());
        }
    }

    @GetMapping("/post/readAll")
    public ResponseFormat readAll() {
        return new ResponseFormat(ResponseType.POST_GET, this.postService.readAllPost());
    }

    @PostMapping("/post/create")
    public ResponseFormat createPost(@RequestBody Post post) {
        try {
            return new ResponseFormat(ResponseType.POST_ADD, this.postService.createPost(post));
        } catch (Exception e) {
            return new ResponseFormat(ResponseType.FAIL, e.getMessage());
        }
    }

    @PutMapping("/post/update")
    public ResponseFormat updatePost(@RequestBody Post post) {
        try {
            return new ResponseFormat(ResponseType.POST_UPDATE, this.postService.updatePost(post));
        } catch (Exception e) {
            return new ResponseFormat(ResponseType.FAIL, e.getMessage());
        }
    }

    @DeleteMapping("/post/remove/{id}")
    public ResponseFormat removePost(@PathVariable Long id) {
        try {
            return new ResponseFormat(ResponseType.POST_DELETE, this.postService.removePost(id));
        } catch (Exception e) {
            return new ResponseFormat(ResponseType.FAIL, e.getMessage());
        }
    }
}
