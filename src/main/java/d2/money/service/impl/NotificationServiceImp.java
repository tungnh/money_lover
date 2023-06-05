package d2.money.service.impl;

import d2.money.domain.Notification;
import d2.money.repository.NotificationRepository;
import d2.money.service.NotificationService;
import d2.money.service.UserService;
import d2.money.service.dto.NotificationDTO;
import d2.money.service.dto.UserDTO;
import d2.money.service.mapper.NotificationMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationServiceImp implements NotificationService {
    private final UserService userService;
    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;

    public NotificationServiceImp(UserService userService, NotificationRepository notificationRepository, NotificationMapper notificationMapper) {
        this.userService = userService;
        this.notificationRepository = notificationRepository;
        this.notificationMapper = notificationMapper;
    }

    @Scheduled(cron = "0 37 15 * * *")
    public void scheduleFixedRateTask() {
        List<UserDTO> users = userService.findAll();
        LocalDate today = LocalDate.now();
        for (UserDTO user : users) {
            LocalDate birthday = user.getBirthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if (birthday.getMonth() == today.getMonth() && birthday.getDayOfMonth() == today.getDayOfMonth()) {
                NotificationDTO notificationDTO = new NotificationDTO();
                notificationDTO.setCreatedDate(new Date());
                notificationDTO.setLastModifiedDate(new Date());
                notificationDTO.setMessage("Chúc Mừng sinh nhật " + user.getUsername() + " !");
                notificationDTO.setTitle("Chúc Mừng");
                notificationDTO.setUserId(user.getId());
                notificationDTO.setType(1);
                save(notificationDTO);
            }
        }
    }

    @Override
    public Optional<NotificationDTO> findById(int id) {
        return notificationRepository.findById(id).map(notificationMapper::toDto);
    }

    @Override
    public List<NotificationDTO> findAllByUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Optional<UserDTO> optionalUser = userService.findByName(userDetails.getUsername());
            return notificationMapper.toDto(notificationRepository.findByUserId(optionalUser.get().getId()));
        }
        return null;
    }

    @Override
    public List<NotificationDTO> findAll() {
        return notificationMapper.toDto(notificationRepository.findAll());
    }

    @Override
    public NotificationDTO save(NotificationDTO notificationDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Optional<UserDTO> optionalUser = userService.findByName(userDetails.getUsername());
            UserDTO userDTO = optionalUser.get();
            for (Integer id : notificationDTO.getUserIdList()) {
                for (UserDTO user : userService.findAll()) {
                    if (id == user.getId()) {
                        Notification notification = notificationMapper.toEntity(notificationDTO);
                        notification.setCreatedBy(userDTO.getUsername());
                        notification.setCreatedDate(new Date());
                        notification.setLastModifiedDate(new Date());
                        notification.setLastModifiedBy(userDTO.getUsername());
                        notification.setUserId(user.getId());
                        notification.setRead(false);
                        notificationRepository.save(notification);
                    }
                }
            }
        }
        return notificationDTO;
    }

    @Override
    public void delete(int id) {
        notificationRepository.deleteById(id);
    }
}