package d2.money.service;
<<<<<<< HEAD
import d2.money.service.dto.UserDTO;
import org.springframework.security.core.Authentication;
import java.util.List;
=======

import d2.money.domain.User;
import d2.money.service.dto.UserDTO;
import org.springframework.security.core.Authentication;

>>>>>>> 764532906c982b035f812176e0cba543bb1975b2
import java.util.Optional;

public interface UserService {
   UserDTO  registerUser(UserDTO userDTO);
   Optional<UserDTO> findById(int id);
   Optional<UserDTO> getUserProfile(Authentication authentication);
<<<<<<< HEAD
=======


import d2.money.service.dto.UserDTO;
import d2.money.service.dto.WalletDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
>>>>>>> 764532906c982b035f812176e0cba543bb1975b2
    List<UserDTO> getAllUserDTO();
    UserDTO save(UserDTO userDTO);
    UserDTO update(UserDTO userDTO);
    void delete(int id);
    Optional<UserDTO> findByUserDTOId(int id);
<<<<<<< HEAD

=======
>>>>>>> 764532906c982b035f812176e0cba543bb1975b2
}
