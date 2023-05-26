package d2.money.service.impl;

import d2.money.domain.User;
import d2.money.repository.UserRepository;
import d2.money.service.dto.UserDTO;
import d2.money.service.mapper.UserMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import d2.money.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

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
        Optional<User> exitEmail = userRepository.findOneByEmail(userDTO.getEmail());
        if (exitEmail.isPresent()) {
            throw new EmailExistsException("Email đã tồn tại");
        }
        Optional<User> exitUserName = userRepository.findOneByUsername(userDTO.getUsername());
        if (exitUserName.isPresent()) {
            throw new UsernameExistsException("Tên đăng nhập đã tồn tại");
        }
        User user = userMapper.toEntity(userDTO);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setCreateDate(new Date());
        user.setStatus(true);
        user.setRole("user");
        userRepository.save(user);
        return userDTO;
    }

    @Override
    public Optional<UserDTO> findById(int id) {
        return userRepository.findById(id).map(userMapper::toDto);
    }

    @Override
    public Optional<UserDTO> getUserProfile(Authentication authentication) {
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername();
            Optional<User> optionalUser = userRepository.findOneByUsername(username);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                UserDTO userDTO = userMapper.toDto(user);
                return Optional.of(userDTO);
            }
        }
        return Optional.empty();
    }

    public class EmailExistsException extends RuntimeException {
        public EmailExistsException(String message) {
            super(message);
        }
    }

    public class UsernameExistsException extends RuntimeException {
        public UsernameExistsException(String message) {
            super(message);
        }
    }
}

