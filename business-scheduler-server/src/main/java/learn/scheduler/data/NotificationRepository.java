package learn.scheduler.data;

import learn.scheduler.models.Notification;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface NotificationRepository {
    @Transactional
    List<Notification> getMessagesByUserId(int userId);

    @Transactional
    Notification addNotification(Notification notification);

    boolean deleteNotification(int notificationId);
}
