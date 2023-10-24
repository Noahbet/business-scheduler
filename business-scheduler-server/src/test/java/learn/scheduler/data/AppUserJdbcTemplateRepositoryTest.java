package learn.scheduler.data;

import learn.scheduler.models.AppUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AppUserJdbcTemplateRepositoryTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    AppUserJdbcTemplateRepository repository;

    @BeforeEach
    void setup() {
        jdbcTemplate.execute("call set_known_good_state();");
    }

    @Test
    void shouldFindByUsername() {
        AppUser expected = makeAppUser(1);

        AppUser actual = repository.findByUsername("appuser1@app.com");

        assertEquals(expected, actual);
    }

    @Test
    void shouldNotFindMissing() {
        AppUser actual = repository.findByUsername("appuser7@app.com");
        assertNull(actual);
    }

    @Test
    void shouldAddAppUser() {
        AppUser appUser = new AppUser(0, "test4@app.com", "hashed_pass_3", true, List.of("TEST_ROLE_1"));
        AppUser expected = new AppUser(4, "test4@app.com", "hashed_pass_3", true, List.of("TEST_ROLE_1"));

        AppUser actual = repository.add(appUser);

        assertEquals(expected, actual);

        assertEquals(expected, repository.findByUsername("test4@app.com"));
    }

    public static AppUser makeAppUser(int id) {
        return new AppUser(
                id,
                String.format("appuser%s@app.com", id),
                String.format("password_hash_%s", id),
                true,
                List.of(String.format("TEST_ROLE_%s", id))
        );
    }
}