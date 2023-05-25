package d2.money.service.impl;

import d2.money.domain.User;
import d2.money.repository.UserRepository;
import d2.money.service.dto.RegisterDTO;
import d2.money.service.dto.UserDTO;
import d2.money.service.util.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {
    public final UserRepository userRepository;
    public final PasswordEncoder passwordEncoder;

    public UserServiceImp(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDTO registerUser(RegisterDTO registerResquest) {
        if (userRepository.findOneByUsername(registerResquest.getUsername()) != null) {
            throw new IllegalArgumentException("Tên người dùng đã tồn tại");
        }
        User user = new User();
        user.setUsername(registerResquest.getUsername());
        user.setPassword(passwordEncoder.encode(registerResquest.getPassword()));
        user.setFirstName(registerResquest.getFirstName());
        user.setLastName(registerResquest.getLastName());
        user.setEmail(registerResquest.getEmail());
        user.setBirthday(registerResquest.getBirthday());
        user.setCreateDate(new Date());
        user.setStatus(true);
        user.setRole("user");
        return userRepository.save()
    }
}

