package d2.money.service;

import d2.money.service.dto.NotificationDTO;

import java.util.List;
import java.util.Optional;

public interface NotificationService {
    Optional<NotificationDTO> findById(int id);
    List<NotificationDTO> findAllByUser();
    List<NotificationDTO> findAll();
    NotificationDTO save(NotificationDTO notificationDTO);
    void delete(int id);
}