package d2.money.service;

import d2.money.domain.Friend;
import d2.money.service.dto.FriendDTO;

import java.util.List;
import java.util.Optional;

public interface FriendService {
    Optional<FriendDTO> findById(int id);
    List<FriendDTO> findAll();
    FriendDTO save(int friendId);
    FriendDTO update(FriendDTO friendDTO);
    void delete(int id);
}