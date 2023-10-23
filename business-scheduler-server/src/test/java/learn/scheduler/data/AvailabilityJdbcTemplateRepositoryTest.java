package learn.scheduler.data;

import learn.scheduler.models.Availability;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class AvailabilityJdbcTemplateRepositoryTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    AvailabilityJdbcTemplateRepository repository;

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
        Availability actual = repository.searchByBusinessId(1);

        assertEquals(LocalTime.of(9, 0), actual.getMondayEnd());
    }

    @Test
    void shouldAddAvailability() {
        Availability expected = makeAvailability();
        expected.setAvailabilityId(0);
        Availability actual = repository.addAvailability(expected);

        Availability actualSearched = repository.searchByBusinessId(1);

        assertEquals(expected.getFridayBreakStart(), actual.getFridayBreakStart());
        assertEquals(1, actual.getAvailabilityId());
    }

    @Test
    void shouldAddUpdateBusiness() {
        Availability availability = makeAvailability();
        availability.setBusinessId(2);
        repository.addAvailability(availability);

        Availability expected= makeAvailability();
        expected.setBusinessId(2);
        expected.setFridayStart(LocalTime.of(5, 0));
        repository.updateAvailability(expected);
        assertTrue(repository.updateAvailability(expected));

        Availability actual = repository.searchByBusinessId(2);
        assertEquals(LocalTime.of(5, 0), actual.getFridayStart());
    }


    private Availability makeAvailability() {
        return new Availability(1,
                LocalTime.of(9, 0), LocalTime.of(9, 0),
                LocalTime.of(0, 0), LocalTime.of(0, 0),
                LocalTime.of(9, 0), LocalTime.of(9, 0),
                LocalTime.of(0, 0), LocalTime.of(0, 0),
                LocalTime.of(9, 0), LocalTime.of(9, 0),
                LocalTime.of(0, 0), LocalTime.of(0, 0),
                LocalTime.of(9, 0), LocalTime.of(9, 0),
                LocalTime.of(0, 0), LocalTime.of(0, 0),
                LocalTime.of(9, 0), LocalTime.of(9, 0),
                LocalTime.of(0, 0), LocalTime.of(0, 0),
                LocalTime.of(9, 0), LocalTime.of(9, 0),
                LocalTime.of(0, 0), LocalTime.of(0, 0),
                LocalTime.of(9, 0), LocalTime.of(9, 0),
                LocalTime.of(0, 0), LocalTime.of(0, 0)
        );
    }
}
