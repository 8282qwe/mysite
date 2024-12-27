package mysite.service;

import mysite.repository.UserRepository;
import mysite.vo.UserVo;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void joinUser(UserVo userVo) {
        userRepository.insert(userVo);
    }

    public UserVo getUser(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    public UserVo getUser(Long id) {
        return userRepository.findById(id);
    }

    public void updateUser(UserVo userVo) {
        userRepository.updateById(userVo);
    }
}
