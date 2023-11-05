package learn.scheduler.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class RatingJdbcTemplateRepositoryTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    RatingJdbcTemplateRepository repository;

    static boolean hasRun = false;

    @BeforeEach
    void setup() {
        if (!hasRun) {
            jdbcTemplate.update("call set_known_good_state();");
            hasRun = true;
        }
    }

    @Test
    void shouldAddRating() {
        assertEquals(5, repository.addRating(2, 2, 5));
    }

    @Test
    void shouldCountRatings() {
        int actual = repository.countRatingsForBusiness(2);

        assertEquals(2, actual);
    }

    @Test
    void shouldGetRating() {
        double actual = repository.getAvgRatingById(2);

        assertEquals(3, Math.round(actual * 10.0) / 10.0);
    }

    @Test
    void shouldUpdateRating() {
        repository.updateRating(2, 1, 5);

        double actual = repository.getAvgRatingById(2);

        assertEquals(5, Math.round(actual * 10.0) / 10.0);
    }
}
