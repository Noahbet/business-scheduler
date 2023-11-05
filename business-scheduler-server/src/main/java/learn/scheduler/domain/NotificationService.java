package learn.scheduler.domain;

import learn.scheduler.data.NotificationRepository;
import learn.scheduler.models.Notification;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class NotificationService {

    private final NotificationRepository repository;

    private final Validator validator;

    public NotificationService(NotificationRepository repository, Validator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    public List<Notification> searchByUserId(int userId) {
        return repository.getMessagesByUserId(userId);
    }

    public Result<Notification> addNotification(Notification notification) {
        Result<Notification> result = validate(notification);
        if (!result.isSuccess()) {
            return result;
        }

        if (notification.getNotificationId() != 0) {
            result.addMessage(ActionStatus.INVALID, "NotificationId cannot be set for `add` operation");
            return result;
        }

        notification = repository.addNotification(notification);
        result.setPayload(notification);
        return result;
    }

    public Result<Notification> deleteNotification(int notificationId) {
        Result<Notification> result = new Result<>();
        boolean deleted = repository.deleteNotification(notificationId);
        if (!deleted) {
            result.addMessage(ActionStatus.NOT_FOUND, "NotificationId `" + notificationId + "` not found.");
        }
        return result;
    }

    private Result<Notification> validate(Notification notification) {

        Result<Notification> result = new Result<>();

        if (notification == null) {
            result.addMessage(ActionStatus.INVALID, "Appointment cannot be null.");
            return result;
        }

        Set<ConstraintViolation<Notification>> violations = validator.validate(notification);

        for (ConstraintViolation<Notification> violation : violations) {
            result.addMessage(ActionStatus.INVALID, violation.getMessage());
        }

        return result;
    }
}
