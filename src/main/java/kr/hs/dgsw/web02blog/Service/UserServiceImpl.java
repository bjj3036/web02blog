package kr.hs.dgsw.web02blog.Service;

import kr.hs.dgsw.web02blog.Domain.Attachment;
import kr.hs.dgsw.web02blog.Domain.Post;
import kr.hs.dgsw.web02blog.Domain.User;
import kr.hs.dgsw.web02blog.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) throws Exception {
        if (this.userRepository.findByAccount(user.getAccount()).isPresent())
            throw new Exception("Already exist User same Account");
        return this.userRepository.save(user);
    }

    @Override
    public User readUser(Long id) throws Exception {
        Optional<User> found = this.userRepository.findById(id);
        if (!found.isPresent())
            throw new Exception("Can not find User");
        return found.get();
    }

    @Override
    public User readUser(String account) throws Exception {
        Optional<User> found = this.userRepository.findByAccount(account);
        if (!found.isPresent())
            throw new Exception("Can not find User");
        return found.get();
    }

    @Override
    public List<User> readAllUser() {
        return this.userRepository.findAll();
    }

    @Override
    public User updateUser(User user) throws Exception {
        User found = this.userRepository.findById(user.getId()).map(user1 -> {
            if (!user.getEmail().isEmpty())
                user1.setEmail(user.getEmail());
            if (!user.getName().isEmpty())
                user1.setName(user.getName());
            if (!user.getPhone().isEmpty())
                user1.setPhone(user.getPhone());
            return user1;
        }).orElse(null);
        if (found == null)
            throw new Exception("Can not find User");
        this.userRepository.save(found);
        return null;
    }

    @Override
    public boolean removeUser(Long id) throws Exception {
        this.userRepository.deleteById(id);
        return true;
    }

    @Override
    public void showProfile(Long id, HttpServletRequest req, HttpServletResponse res) {

    }

    @Override
    public Attachment uploadProfile(Long id, MultipartFile uploadFile) throws Exception{
        return null;
    }
}
