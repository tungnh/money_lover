package d2.money.service.mapper;

import d2.money.domain.User;
import d2.money.service.dto.UserDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper implements EntityMapper<UserDTO, User> {
    @Override
    public User toEntity(UserDTO dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setCreateDate(dto.getCreateDate());
        user.setBirthday(dto.getBirthday());
        user.setStatus(dto.isStatus());
        user.setImage(dto.getImage());
        user.setRole(dto.getRole());
        user.setProvinderType(dto.getProvinderType());
        user.setProvinderId(dto.getProvinderId());
        user.setAccsessToken(dto.getAccsessToken());
        return user;
    }

    @Override
    public UserDTO toDto(User entity) {
        UserDTO user = new UserDTO();
        user.setId(entity.getId());
        user.setUsername(entity.getUsername());
        user.setPassword(entity.getPassword());
        user.setFirstName(entity.getFirstName());
        user.setLastName(entity.getLastName());
        user.setEmail(entity.getEmail());
        user.setCreateDate(entity.getCreateDate());
        user.setBirthday(entity.getBirthday());
        user.setStatus(entity.isStatus());
        user.setImage(entity.getImage());
        user.setRole(entity.getRole());
        user.setProvinderType(entity.getProvinderType());
        user.setProvinderId(entity.getProvinderId());
        user.setAccsessToken(entity.getAccsessToken());
        return user;
    }

    @Override
    public List<User> toEntity(List<UserDTO> dtoList) {
        if (dtoList.isEmpty()) {
            return null;
        }
        List<User> listUser = new ArrayList<>(dtoList.size());
        for (UserDTO userDTO : dtoList) {
            listUser.add(toEntity(userDTO));
        }
        return listUser;
    }

    @Override
    public List<UserDTO> toDto(List<User> entityList) {
        if (entityList == null) {
            return null;
        }
        List<UserDTO> listuser = new ArrayList<>(entityList.size());
        for (User user : entityList) {
            listuser.add(toDto(user));
        }
        return listuser;
    }

    @Override
    public void partialUpdate(User entity, UserDTO dto) {

    }
}