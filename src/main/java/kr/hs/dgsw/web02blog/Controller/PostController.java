package kr.hs.dgsw.web02blog.Controller;

import kr.hs.dgsw.web02blog.Domain.Post;
import kr.hs.dgsw.web02blog.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/post/readById/{id}")
    public Post readById(@PathVariable Long id) {
        return this.postService.readPost(id);
    }

    @GetMapping("/post/readAll")
    public List<Post> readAll() {
        return this.postService.readAllPost();
    }

    @PostMapping("/post/create")
    public Post createPost(@RequestBody Post post) {
        return this.postService.createPost(post);
    }

    @PutMapping("/post/update")
    public Post updatePost(@RequestBody Post post) {
        return this.postService.updatePost(post);
    }

    @DeleteMapping("/post/remove/{id}")
    public boolean removePost(@PathVariable Long id) {
        return this.postService.removePost(id);
    }
}
