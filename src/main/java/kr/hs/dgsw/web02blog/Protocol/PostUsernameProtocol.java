package kr.hs.dgsw.web02blog.Protocol;

import kr.hs.dgsw.web02blog.Domain.Post;

public class PostUsernameProtocol
        extends Post {
    private String username;

    public PostUsernameProtocol(Post c, String username){
        super(c);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
