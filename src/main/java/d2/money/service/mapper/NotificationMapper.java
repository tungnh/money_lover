package d2.money.service.mapper;

import d2.money.domain.Notification;
import d2.money.service.dto.NotificationDTO;

import java.util.List;

public class NotificationMapper implements EntityMapper<NotificationDTO, Notification> {
    @Override
    public Notification toEntity(NotificationDTO dto) {
        return null;
    }

    @Override
    public NotificationDTO toDto(Notification entity) {
        return null;
    }

    @Override
    public List<Notification> toEntity(List<NotificationDTO> dtoList) {
        return null;
    }

    @Override
    public List<NotificationDTO> toDto(List<Notification> entityList) {
        return null;
    }

    @Override
    public void partialUpdate(Notification entity, NotificationDTO dto) {

    }
}