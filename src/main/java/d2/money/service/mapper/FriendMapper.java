package d2.money.service.mapper;

import d2.money.domain.Friend;
import d2.money.service.dto.FriendDTO;

import java.util.List;

public class FriendMapper implements EntityMapper<FriendDTO, Friend> {
    @Override
    public Friend toEntity(FriendDTO dto) {
        return null;
    }

    @Override
    public FriendDTO toDto(Friend entity) {
        return null;
    }

    @Override
    public List<Friend> toEntity(List<FriendDTO> dtoList) {
        return null;
    }

    @Override
    public List<FriendDTO> toDto(List<Friend> entityList) {
        return null;
    }

    @Override
    public void partialUpdate(Friend entity, FriendDTO dto) {

    }
}