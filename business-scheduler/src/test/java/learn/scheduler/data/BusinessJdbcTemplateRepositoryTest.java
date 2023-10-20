package learn.scheduler.data;

import learn.scheduler.models.Business;
import learn.scheduler.models.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BusinessJdbcTemplateRepositoryTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    BusinessJdbcTemplateRepository repository;

    static boolean hasRun = false;

    @BeforeEach
    void setup() {
        if (!hasRun) {
            jdbcTemplate.update("call set_known_good_state();");
            hasRun = true;
        }
    }

    @Test
    void shouldSearchBusinesses() {
        List<Business> actual = repository.searchBusinesses("Bus");

        assertEquals(3, actual.size());
        assertTrue(actual.stream()
                .anyMatch(o -> o.getBusinessId() == 1 && o.getBusinessName().equals("Business 1")));
    }

    @Test
    void shouldSearchByCategory() {
        List<Business> actual = repository.searchByCategory(Category.SERVICE);

        assertEquals(1, actual.size());
        assertTrue(actual.stream()
                .anyMatch(o -> o.getBusinessId() == 3 && o.getBusinessName().equals("Business 3")));
    }

    @Test
    void shouldFindById() {
        Business actual = repository.searchById(1);

        assertNotNull(actual);
    }

    @Test
    void shouldAddBusiness() {
        Business expected = makeBusiness();
        repository.addBusiness(expected);
        expected.setBusinessId(4);

        List<Business> actual = repository.searchBusinesses("Test");

        assertEquals(expected, actual.get(0));
    }

    @Test
    void shouldAddUpdateBusiness() {
        assertTrue(repository.updateBusinessName(1, "Noah's One Stop Shop"));
        List<Business> actual = repository.searchBusinesses("Noah's One Stop Shop");

        assertEquals("Noah's One Stop Shop", actual.get(0).getBusinessName());
    }

    @Test
    void shouldDeleteBusiness() {
        assertTrue(repository.deleteBusiness(4));

        List<Business> actual = repository.searchBusinesses("Test");

        assertEquals(0, actual.size());
    }

    private Business makeBusiness() {

        return new Business("Test", 1, Category.RESTAURANT);
    }
}
