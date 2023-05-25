package d2.money.service.impl;

import d2.money.domain.User;
import d2.money.repository.UserRepository;
import d2.money.service.dto.UserDTO;
import d2.money.service.mapper.UserMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import d2.money.service.UserService;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class UserServiceImp implements UserService {
    public final UserRepository userRepository;
    public final PasswordEncoder passwordEncoder;
    public final UserMapper userMapper;

    public UserServiceImp(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    @Override
    public UserDTO registerUser(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setCreateDate(new Date());
        user.setStatus(true);
        user.setRole("user");
        userRepository.save(user);
        return userDTO;
    }
}

