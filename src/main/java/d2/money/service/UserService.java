package d2.money.service;

import d2.money.domain.User;
import d2.money.service.dto.UserDTO;
import org.springframework.security.core.Authentication;

import java.util.List;

import java.util.Optional;

public interface UserService {
    UserDTO registerUser(UserDTO userDTO);

    Optional<UserDTO> findOneByUsername(String name);

    Optional<UserDTO> findOneByEmail(String email);

    Optional<UserDTO> findById(int id);

    Optional<UserDTO> findByName(String name);
    Optional<UserDTO> findByRole(String role);

    Optional<UserDTO> getUserProfile(Authentication authentication);

    List<UserDTO> findAll();

    List<UserDTO> findAllUser();

    UserDTO save(UserDTO userDTO);

    void delete(int id);

    UserDTO update(UserDTO userDTO, Authentication authentication);
}