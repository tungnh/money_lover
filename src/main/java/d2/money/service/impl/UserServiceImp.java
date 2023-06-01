package d2.money.service.impl;

import d2.money.domain.User;
import d2.money.repository.UserRepository;
import d2.money.service.dto.UserDTO;
import d2.money.service.mapper.UserMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import d2.money.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
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
    public Optional<UserDTO> findOneByUsername(String name) {
        return userRepository.findOneByUsername(name).map(userMapper::toDto);
    }

    @Override
    public Optional<UserDTO> findOneByEmail(String email) {
        return userRepository.findOneByEmail(email).map(userMapper::toDto);
    }

    @Override
    public Optional<UserDTO> findById(int id) {
        return userRepository.findById(id).map(userMapper::toDto);
    }

    @Override
    public Optional<UserDTO> findByName(String name) {
        return userRepository.findOneByUsername(name).map(userMapper::toDto);
    }

    @Override
    public Optional<UserDTO> getUserProfile(Authentication authentication) {
        String username = authentication.getName();
        Optional<User> userOpt = userRepository.findOneByUsername(username);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            UserDTO userDTO = userMapper.toDto(user);
            return Optional.of(userDTO);
        } else {
            return Optional.empty();
        }
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

    @Override
    public List<UserDTO> findAll() {
        return userMapper.toDto(userRepository.findAll());
    }

    @Override
    public List<UserDTO> findAllUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Optional<User> optionalUser = userRepository.findOneByUsername(userDetails.getUsername());
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                return userMapper.toDto(userRepository.findAllUsersExceptId(user.getId()));
            }
        }
        return null;
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        return null;
    }

    @Override
    public UserDTO update(UserDTO userDTO, Authentication authentication) {
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername();
            Optional<User> optionalUser = userRepository.findOneByUsername(username);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                user.setFirstName(userDTO.getFirstName());
                user.setLastName(userDTO.getLastName());
                user.setEmail(userDTO.getEmail());
                if (userDTO.getBirthday() != null) {
                    user.setBirthday(userDTO.getBirthday());
                }
                user.setImage(userDTO.getImage());
                try {
                    User updatedUser = userRepository.save(user);
                    return userMapper.toDto(updatedUser);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public void delete(int id) {
    }
}