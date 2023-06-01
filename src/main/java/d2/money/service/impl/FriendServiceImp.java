package d2.money.service.impl;

import d2.money.domain.Friend;
import d2.money.domain.User;
import d2.money.repository.FriendRepository;
import d2.money.repository.UserRepository;
import d2.money.service.FriendService;
import d2.money.service.dto.FriendDTO;
import d2.money.service.mapper.FriendMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FriendServiceImp implements FriendService {
    private final FriendRepository friendRepository;
    private final UserRepository userRepository;
    private final FriendMapper friendMapper;

    public FriendServiceImp(FriendRepository friendRepository, UserRepository userRepository, FriendMapper friendMapper) {
        this.friendRepository = friendRepository;
        this.userRepository = userRepository;
        this.friendMapper = friendMapper;
    }

    @Override
    public Optional<FriendDTO> findById(int id) {
        return Optional.empty();
    }

    @Override
    public List<FriendDTO> findAll() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Optional<User> optionalUser = userRepository.findOneByUsername(userDetails.getUsername());
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                return friendMapper.toDto(friendRepository.findByUserIdAndAndState(user.getId(), 1));
            }
        }
        return null;
    }

    @Override
    public FriendDTO save(int friendId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Optional<User> optionalUser = userRepository.findOneByUsername(userDetails.getUsername());
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                User userFindByFriend = userRepository.findById(friendId).get();
                Friend friend = new Friend();
                friend.setFriend(userFindByFriend);
                friend.setUser(user);
                friend.setState(1);
                Friend savedFriend = friendRepository.save(friend);
                FriendDTO friendDTO = new FriendDTO();
                friendDTO.setFriendId(savedFriend.getFriend().getId());
                friendDTO.setUserId(savedFriend.getUser().getId());
                friendDTO.setState(savedFriend.getState());
                return friendDTO;
            }
        }
        return null;
    }

    @Override
    public FriendDTO update(FriendDTO friendDTO) {
        return null;
    }

    @Override
    public void delete(int id) {
    }
}