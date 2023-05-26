package d2.money.service;

import d2.money.domain.User;
import d2.money.service.dto.UserDTO;
import org.springframework.security.core.Authentication;

import java.util.Optional;

public interface UserService {
   UserDTO  registerUser(UserDTO userDTO);
   Optional<UserDTO> findById(int id);
   Optional<UserDTO> getUserProfile(Authentication authentication);


import d2.money.service.dto.UserDTO;
import d2.money.service.dto.WalletDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDTO> getAllUserDTO();
    UserDTO save(UserDTO userDTO);
    UserDTO update(UserDTO userDTO);
    void delete(int id);
    Optional<UserDTO> findByUserDTOId(int id);
}
