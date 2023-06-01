package d2.money.service.mapper;

import d2.money.domain.Notification;
import d2.money.service.dto.NotificationDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NotificationMapper implements EntityMapper<NotificationDTO, Notification> {
    @Override
    public Notification toEntity(NotificationDTO dto) {
        Notification notification = new Notification();
        notification.setId(dto.getId());
        notification.setCreatedBy(dto.getCreatedBy());
        notification.setCreatedDate(dto.getCreatedDate());
        notification.setRead(dto.isRead());
        notification.setLastModifiedBy(dto.getLastModifiedBy());
        notification.setLastModifiedDate(dto.getLastModifiedDate());
        notification.setMessage(dto.getMessage());
        notification.setReadedAt(dto.getReadedAt());
        notification.setTitle(dto.getTitle());
        notification.setType(dto.getType());
        notification.setUserId(dto.getUserId());
        return notification;
    }

    @Override
    public NotificationDTO toDto(Notification entity) {
        NotificationDTO notification = new NotificationDTO();
        notification.setId(entity.getId());
        notification.setCreatedBy(entity.getCreatedBy());
        notification.setCreatedDate(entity.getCreatedDate());
        notification.setRead(entity.isRead());
        notification.setLastModifiedBy(entity.getLastModifiedBy());
        notification.setLastModifiedDate(entity.getLastModifiedDate());
        notification.setMessage(entity.getMessage());
        notification.setReadedAt(entity.getReadedAt());
        notification.setTitle(entity.getTitle());
        notification.setType(entity.getType());
        notification.setUserId(entity.getUserId());
        return notification;
    }

    @Override
    public List<Notification> toEntity(List<NotificationDTO> dtoList) {
        if (dtoList.isEmpty()) {
            return null;
        }
        List<Notification> notificationList = new ArrayList<>(dtoList.size());
        for (NotificationDTO notificationDTO : dtoList) {
            notificationList.add(toEntity(notificationDTO));
        }
        return notificationList;
    }

    @Override
    public List<NotificationDTO> toDto(List<Notification> entityList) {
        if (entityList.isEmpty()) {
            return null;
        }
        List<NotificationDTO> notificationDTOList = new ArrayList<>(entityList.size());
        for (Notification notification : entityList) {
            notificationDTOList.add(toDto(notification));
        }
        return notificationDTOList;
    }

    @Override
    public void partialUpdate(Notification entity, NotificationDTO dto) {
    }
}