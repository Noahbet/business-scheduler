package learn.scheduler.domain;

import learn.scheduler.data.AppointmentRepository;
import learn.scheduler.data.AvailabilityRepository;
import learn.scheduler.data.BusinessRepository;
import learn.scheduler.data.ServiceRepository;
import learn.scheduler.models.Appointment;
import learn.scheduler.models.Availability;
import learn.scheduler.models.Business;
import learn.scheduler.models.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.validation.Validator;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AvailabilityServiceTest {

    @MockBean
    AvailabilityRepository repository;
    @MockBean
    BusinessRepository businessRepository;
    @Autowired
    AvailabilityService service;

    @Test
    void shouldFindBusinessId() {
        Availability expected = makeAvailability();
        when(repository.searchByBusinessId(1)).thenReturn(expected);

        Availability actual = service.searchByBusinessId(1);

        assertEquals(expected, actual);
    }

    @Test
    void shouldAddValidAvailability() {
        Availability availability = makeAvailability();
        Availability expected = makeAvailability();
        expected.setAvailabilityId(1);

        when(businessRepository.searchById(1)).thenReturn(makeBusiness());
        when(repository.addAvailability(availability)).thenReturn(expected);

        Result<Availability> actual = service.addAvailability(availability);

        assertTrue(actual.isSuccess());
        assertEquals(expected, actual.getPayload());
    }

    @Test
    void shouldNotAddWithInvalidBusinessId() {
        Availability availability =  makeAvailability();

        when(businessRepository.searchById(1)).thenReturn(null);
        Result<Availability> actual = service.addAvailability(availability);

        assertFalse(actual.isSuccess());
    }

    @Test
    void shouldNotAddWithZeroId() {
        Availability availability =  makeAvailability();
        availability.setAvailabilityId(1);

        when(businessRepository.searchById(1)).thenReturn(null);
        Result<Availability> actual = service.addAvailability(availability);

        assertFalse(actual.isSuccess());
    }

    @Test
    void shouldNotAddWithNullMondayStart() {
        Availability availability =  makeAvailability();
        availability.setMondayStart(null);

        Result<Availability> actual = service.addAvailability(availability);

        assertFalse(actual.isSuccess());
    }

    @Test
    void shouldNotAddWithMondayStartAfterMondayEnd() {
        Availability availability =  makeAvailability();
        availability.setMondayStart(LocalTime.of(23, 59));

        Result<Availability> actual = service.addAvailability(availability);

        assertFalse(actual.isSuccess());
    }

    @Test
    void shouldNotAddWithZeroBusinessId() {
        Availability availability =  makeAvailability();
        availability.setBusinessId(0);

        Result<Availability> actual = service.addAvailability(availability);

        assertFalse(actual.isSuccess());
    }

    @Test
    void shouldNotAddNull() {
        Result<Availability> actual = service.addAvailability(null);

        assertFalse(actual.isSuccess());
    }

    @Test
    void shouldNotUpdateAvailability() {
        Availability availability = makeAvailability();
        Availability expected = makeAvailability();
        expected.setAvailabilityId(1);

        when(businessRepository.searchById(1)).thenReturn(makeBusiness());
        when(repository.updateAvailability(availability)).thenReturn(false);

        Result<Availability> actual = service.updateAvailability(availability);

        assertFalse(actual.isSuccess());
    }

    @Test
    void shouldUpdateValidAvailability() {
        Availability availability = makeAvailability();
        Availability expected = makeAvailability();
        expected.setAvailabilityId(1);

        when(businessRepository.searchById(1)).thenReturn(makeBusiness());
        when(repository.updateAvailability(availability)).thenReturn(true);

        Result<Availability> actual = service.updateAvailability(availability);

        assertTrue(actual.isSuccess());
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

    private Business makeBusiness() {

        return new Business(1, "Test", 1, Category.HAIR);
    }
}
