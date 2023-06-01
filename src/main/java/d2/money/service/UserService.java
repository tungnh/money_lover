package d2.money.service;

import d2.money.service.dto.UserDTO;
import org.springframework.security.core.Authentication;
import java.util.List;

import java.util.Optional;

public interface UserService {
    UserDTO registerUser(UserDTO userDTO);
    Optional<UserDTO> findById(int id);
    Optional<UserDTO> findByName(String name);
    Optional<UserDTO> getUserProfile(Authentication authentication);
    List<UserDTO> findAll();
    List<UserDTO> findAllUser();
    UserDTO save(UserDTO userDTO);
    UserDTO update(UserDTO userDTO);
    void delete(int id);
}