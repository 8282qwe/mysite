package mysite.service;

import mysite.repository.UserRepository;
import mysite.vo.UserVo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void joinUser(UserVo userVo) {
        userVo.setPassword(passwordEncoder.encode(userVo.getPassword()));
        userRepository.insert(userVo);
    }

    public UserVo getUser(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    public UserVo getUser(Long id) {
        return userRepository.findById(id);
    }

    public void updateUser(UserVo userVo) {
        if (userVo.getPassword().isBlank() || userVo.getPassword().isEmpty() || userVo.getPassword() != null) {
            userVo.setPassword(passwordEncoder.encode(userVo.getPassword()));
        }
        userRepository.updateById(userVo);
    }

    public UserVo getUser(String email) {
        UserVo userVo = userRepository.findByEmail(email, UserVo.class);
        if (userVo == null) {
            return null;
        }
        userVo.setPassword(null);
        return userVo;
    }
}
