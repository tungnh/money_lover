package d2.money.service.mapper;

import d2.money.domain.User;
import d2.money.service.dto.UserDTO;

import java.util.List;

public class UserMapper implements EntityMapper<UserDTO, User> {
    @Override
    public User toEntity(UserDTO dto) {
        return null;
    }

    @Override
    public UserDTO toDto(User entity) {
        return null;
    }

    @Override
    public List<User> toEntity(List<UserDTO> dtoList) {
        return null;
    }

    @Override
    public List<UserDTO> toDto(List<User> entityList) {
        return null;
    }

    @Override
    public void partialUpdate(User entity, UserDTO dto) {

    }
}
