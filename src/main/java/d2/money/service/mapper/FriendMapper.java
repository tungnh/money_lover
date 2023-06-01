package d2.money.service.mapper;

import d2.money.domain.Friend;
import d2.money.service.dto.FriendDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class FriendMapper implements EntityMapper<FriendDTO, Friend> {
    @Override
    public Friend toEntity(FriendDTO dto) {
        Friend friend=new Friend();
        friend.setState(dto.getState());
        return friend;
    }

    @Override
    public FriendDTO toDto(Friend entity) {
        FriendDTO friendDTO=new FriendDTO();
        friendDTO.setFriendId(entity.getFriend().getId());
        friendDTO.setUserId(entity.getUser().getId());
        friendDTO.setState(entity.getState());
        return friendDTO;
    }

    @Override
    public List<Friend> toEntity(List<FriendDTO> dtoList) {
        if (dtoList.isEmpty()) {
            return null;
        }
        List<Friend> friendList = new ArrayList<>(dtoList.size());
        for (FriendDTO friendDTO : dtoList) {
            friendList.add(toEntity(friendDTO));
        }
        return friendList;
    }

    @Override
    public List<FriendDTO> toDto(List<Friend> entityList) {
        if (entityList.isEmpty()) {
            return null;
        }
        List<FriendDTO> friendDTOList = new ArrayList<>(entityList.size());
        for (Friend friendDTO : entityList) {
            friendDTOList.add(toDto(friendDTO));
        }
        return friendDTOList;
    }

    @Override
    public void partialUpdate(Friend entity, FriendDTO dto) {
    }
}