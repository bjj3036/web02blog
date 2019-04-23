package kr.hs.dgsw.web02blog.Controller;

import kr.hs.dgsw.web02blog.Protocol.ResponseFormat;
import kr.hs.dgsw.web02blog.Protocol.ResponseType;
import kr.hs.dgsw.web02blog.Service.PostService;
import kr.hs.dgsw.web02blog.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class AttachmentController {

    @Autowired
    UserService userService;
    @Autowired
    PostService postService;

    @PostMapping("/attachment/{id}")
    public ResponseFormat upload(@PathVariable Long id, @RequestPart MultipartFile uploadFile) {
        try {
            return new ResponseFormat(ResponseType.ATTACHMENT_STROED, this.userService.uploadProfile(id, uploadFile));
        } catch (Exception e) {
            return new ResponseFormat(ResponseType.FAIL, e.getMessage());
        }
    }

    @PostMapping("/attachmentPost")
    public ResponseFormat uploadPostImage(@RequestPart MultipartFile uploadFile) {
        try {
            return new ResponseFormat(ResponseType.ATTACHMENT_STROED, this.postService.uploadCommentImage(uploadFile));
        } catch (Exception e) {
            return new ResponseFormat(ResponseType.FAIL, e.getMessage());
        }
    }

    @GetMapping("/attachment/{type}/{id}")
    public void download(@PathVariable String type, @PathVariable Long id, HttpServletRequest req, HttpServletResponse res) {
        if ("user".equals(type))
            this.userService.showProfile(id, req, res);
        else {
            this.postService.showCommentImage(id, req, res);
        }
    }
}
