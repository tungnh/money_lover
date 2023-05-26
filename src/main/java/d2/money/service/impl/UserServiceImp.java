package d2.money.service.impl;

import d2.money.service.UserService;
import d2.money.service.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {
    @Override
    public List<UserDTO> getAllUserDTO() {
        return null;
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        return null;
    }

    @Override
    public UserDTO update(UserDTO userDTO) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Optional<UserDTO> findByUserDTOId(int id) {
        return Optional.empty();
    }
}

