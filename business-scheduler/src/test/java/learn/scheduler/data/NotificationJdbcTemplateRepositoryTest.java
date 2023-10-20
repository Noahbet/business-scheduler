package learn.scheduler.data;

import learn.scheduler.models.Appointment;
import learn.scheduler.models.Notification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class NotificationJdbcTemplateRepositoryTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    NotificationJdbcTemplateRepository repository;

    static boolean hasRun = false;

    @BeforeEach
    void setup() {
        if (!hasRun) {
            jdbcTemplate.update("call set_known_good_state();");
            hasRun = true;
        }
    }

    @Test
    void shouldGetByUserId() {
        List<Notification> actual = repository.getMessagesByUserId(3);

        assertEquals(1, actual.size());
        assertEquals("I in hospital", actual.get(0).getMessage());
    }

    @Test
    void shouldAddNotification() {
        Notification expected = makeNotification();
        repository.addNotification(expected);

        List<Notification> actual = repository.getMessagesByUserId(1);

        assertEquals(1, actual.size());
    }

    @Test
    void shouldDeleteNotification() {
        assertTrue(repository.deleteNotification(1));

        List<Notification> actual = repository.getMessagesByUserId(2);
        assertNotEquals(1, actual.get(0).getNotificationId());
    }

    private Notification makeNotification() {
        return new Notification(3, 1, "Nooooooooooo");
    }
}
