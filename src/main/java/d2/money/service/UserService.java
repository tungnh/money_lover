package d2.money.service;

import d2.money.domain.User;
import d2.money.service.dto.UserDTO;
import org.springframework.security.core.Authentication;

import java.util.Optional;

public interface UserService {
   UserDTO  registerUser(UserDTO userDTO);
   Optional<UserDTO> findById(int id);
   Optional<UserDTO> getUserProfile(Authentication authentication);

}
