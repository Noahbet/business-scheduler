package learn.scheduler.domain;

import learn.scheduler.data.AppointmentRepository;
import learn.scheduler.data.BusinessRepository;
import learn.scheduler.data.NotificationRepository;
import learn.scheduler.data.ServiceRepository;
import learn.scheduler.models.Appointment;
import learn.scheduler.models.Notification;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

@SpringBootTest
public class NotificationServiceTest {

    @MockBean
    NotificationRepository repository;
    @Autowired
    NotificationService service;

    @Test
    void shouldFindUserId() {
        List<Notification> expected = List.of(makeNotification(), makeNotification());
        when(repository.getMessagesByUserId(1)).thenReturn(expected);

        List<Notification> actual = service.searchByUserId(1);

        assertEquals(expected, actual);
    }

    @Test
    void shouldAddValidNotification() {
        Notification notification = makeNotification();
        Notification expected = makeNotification();
        expected.setNotificationId(1);

        when(repository.addNotification(notification)).thenReturn(expected);

        Result<Notification> actual = service.addNotification(notification);

        assertTrue(actual.isSuccess());
        assertEquals(expected, actual.getPayload());
    }

    @Test
    void shouldNotAddWithId() {
        Notification notification = makeNotification();
        notification.setNotificationId(1);

        Result<Notification> actual = service.addNotification(notification);

        assertFalse(actual.isSuccess());
    }

    @Test
    void shouldNotAddWithZeroReceiverId() {
        Notification notification = makeNotification();
        notification.setReceiverId(0);

        Result<Notification> actual = service.addNotification(notification);

        assertFalse(actual.isSuccess());
    }

    @Test
    void shouldNotAddWithZeroSenderId() {
        Notification notification = makeNotification();
        notification.setSenderId(0);

        Result<Notification> actual = service.addNotification(notification);

        assertFalse(actual.isSuccess());
    }

    @Test
    void shouldNotAddWithBlankSenderEmail() {
        Notification notification = makeNotification();
        notification.setSenderEmail("");

        Result<Notification> actual = service.addNotification(notification);

        assertFalse(actual.isSuccess());
    }

    @Test
    void shouldNotAddWithBlankReceiverEmail() {
        Notification notification = makeNotification();
        notification.setReceiverEmail("");

        Result<Notification> actual = service.addNotification(notification);

        assertFalse(actual.isSuccess());
    }

    @Test
    void shouldNotAddWithBlankMessage() {
        Notification notification = makeNotification();
        notification.setMessage("");

        Result<Notification> actual = service.addNotification(notification);

        assertFalse(actual.isSuccess());
    }

    private Notification makeNotification() {
        return new Notification(3, "sender@email", 1, "receiver@email", "Nooooooooooo");
    }
}
