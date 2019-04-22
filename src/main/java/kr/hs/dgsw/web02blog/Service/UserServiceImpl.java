package kr.hs.dgsw.web02blog.Service;

import kr.hs.dgsw.web02blog.Domain.Post;
import kr.hs.dgsw.web02blog.Domain.User;
import kr.hs.dgsw.web02blog.Repository.UserRepository;
import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        if (this.userRepository.findByAccount(user.getAccount()).isPresent())
            return null;
        return this.userRepository.save(user);
    }

    @Override
    public User readUser(Long id) {
        return this.userRepository.findById(id).orElse(null);
    }

    @Override
    public User readUser(String account) {
        return this.userRepository.findByAccount(account).orElse(null);
    }

    @Override
    public List<User> readAllUser() {
        return this.userRepository.findAll();
    }

    @Override
    public User updateUser(User user) {
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
            return null;
        this.userRepository.save(found);
        return null;
    }

    @Override
    public boolean removeUser(Long id) {
        try {
            this.userRepository.deleteById(id);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
