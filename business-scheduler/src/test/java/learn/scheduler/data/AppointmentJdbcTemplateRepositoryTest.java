package learn.scheduler.data;

import learn.scheduler.models.Appointment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class AppointmentJdbcTemplateRepositoryTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    AppointmentJdbcTemplateRepository repository;

    static boolean hasRun = false;

    @BeforeEach
    void setup() {
        if (!hasRun) {
            jdbcTemplate.update("call set_known_good_state();");
            hasRun = true;
        }
    }

    @Test
    void shouldSearchByBusinessId() {
        List<Appointment> actual = repository.searchByBusinessId(1);

        assertEquals(2, actual.size());
        assertEquals(1, actual.get(0).getServiceId());
    }

    @Test
    void shouldSearchByUserId() {
        List<Appointment> actual = repository.searchByUserId(1);

        assertEquals(1, actual.size());
        assertEquals(1, actual.get(0).getServiceId());
    }

    @Test
    void shouldAddAppointment() {
        Appointment expected = makeAppointment();
        repository.addAppointment(expected);

        List<Appointment> actual = repository.searchByUserId(2);

        assertEquals(1, actual.size());
    }

    @Test
    void shouldDeleteAppointment() {
        assertTrue(repository.deleteAppointment(2));

        List<Appointment> actual = repository.searchByUserId(2);

        assertEquals(0, actual.size());
    }

    private Appointment makeAppointment() {

        return new Appointment(
                2, 1, 1,
                LocalDateTime.of(2023, 11, 11, 12, 30)
        );
    }
}
