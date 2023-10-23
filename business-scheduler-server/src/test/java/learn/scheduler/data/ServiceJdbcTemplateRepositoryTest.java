package learn.scheduler.data;

import learn.scheduler.models.Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ServiceJdbcTemplateRepositoryTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    ServiceJdbcTemplateRepository repository;

    static boolean hasRun = false;

    @BeforeEach
    void setup() {
        if (!hasRun) {
            jdbcTemplate.update("call set_known_good_state();");
            hasRun = true;
        }
    }

    @Test
    void shouldGetByBusinessId() {
        List<Service> actual = repository.getServicesForBusiness(2);

        assertEquals(2, actual.size());
        assertEquals("Dish 1", actual.get(0).getServiceName());
    }

    @Test
    void shouldGetByServiceId() {
        Service actual = repository.getServiceById(1);

        assertEquals("Service A", actual.getServiceName());
    }

    @Test
    void shouldAddService() {
        Service expected = makeService();
        repository.addService(expected);

        List<Service> actual = repository.getServicesForBusiness(1);

        assertEquals(3, actual.size());
        assertEquals("Service C", actual.get(2).getServiceName());
    }

    @Test
    void shouldDeleteNotification() {
        assertTrue(repository.deleteService(5));

        Service actual = repository.getServiceById(5);
        assertNull(actual);
    }

    private Service makeService() {

        return new Service("Service C", 1, 30, new BigDecimal(90));
    }
}