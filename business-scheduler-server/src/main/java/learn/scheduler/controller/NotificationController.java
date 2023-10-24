package learn.scheduler.controller;

import learn.scheduler.domain.NotificationService;
import learn.scheduler.domain.Result;
import learn.scheduler.models.Business;
import learn.scheduler.models.Category;
import learn.scheduler.models.Notification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/notification")
public class NotificationController {

    private final NotificationService service;

    public NotificationController(NotificationService service) {
        this.service = service;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Notification>> searchByUserId(@PathVariable int userId) {
        List<Notification> notifications = service.searchByUserId(userId);
        if (notifications == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(notifications);
    }

    @PostMapping
    public ResponseEntity<Notification> addNotification(@RequestBody Notification notification) {
        Result<Notification> result = service.addNotification(notification);
        return new ResponseEntity<>(result.getPayload(), getStatus(result, HttpStatus.CREATED));
    }

    @DeleteMapping("/{notificationId}")
    public ResponseEntity<Void> deleteNotification(@PathVariable int notificationId) {
        Result<Notification> result = service.deleteNotification(notificationId);
        return new ResponseEntity<>(getStatus(result, HttpStatus.NO_CONTENT));
    }

    private HttpStatus getStatus(Result<Notification> result, HttpStatus statusDefault) {
        return switch (result.getStatus()) {
            case INVALID -> HttpStatus.PRECONDITION_FAILED;
            case DUPLICATE -> HttpStatus.FORBIDDEN;
            case NOT_FOUND -> HttpStatus.NOT_FOUND;
            default -> statusDefault;
        };
    }
}
