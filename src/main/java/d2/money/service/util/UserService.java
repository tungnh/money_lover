package d2.money.service.util;

import d2.money.domain.User;
import d2.money.service.dto.RegisterDTO;
import d2.money.service.dto.UserDTO;

import java.util.Optional;

public interface UserService {
   UserDTO  registerUser(RegisterDTO registerResquest);
}
