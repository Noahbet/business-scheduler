package learn.scheduler.domain;

import learn.scheduler.data.AppointmentRepository;
import learn.scheduler.data.AvailabilityRepository;
import learn.scheduler.data.BusinessRepository;
import learn.scheduler.data.ServiceRepository;
import learn.scheduler.models.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AppointmentServiceTest {

    @MockBean
    AppointmentRepository repository;
    @MockBean
    BusinessRepository businessRepository;
    @MockBean
    AvailabilityRepository availabilityRepository;
    @MockBean
    ServiceRepository serviceRepository;
    @Autowired
    AppointmentService service;

    @Test
    void shouldFindUserId() {
        List<Appointment> expected = List.of(makeAppointment(), makeAppointment());
        when(repository.searchByUserId(1)).thenReturn(expected);

        List<Appointment> actual = service.searchByUserId(1);

        assertEquals(expected, actual);
    }

    @Test
    void shouldFindBusinessId() {
        List<Appointment> expected = List.of(makeAppointment(), makeAppointment());
        when(repository.searchByBusinessId(1)).thenReturn(expected);

        List<Appointment> actual = service.searchByBusinessId(1);

        assertEquals(expected, actual);
    }

    @Test
    void shouldAddValidAppointment() {
        Appointment appointment = makeAppointment(0);
        Appointment expected = makeAppointment();

        when(availabilityRepository.searchByBusinessId(1)).thenReturn(makeAvailability());
        when(businessRepository.searchById(1)).thenReturn(makeBusiness());
        when(serviceRepository.getServiceById(1)).thenReturn(makeService());
        when(repository.addAppointment(appointment)).thenReturn(expected);

        Result<Appointment> actual = service.addAppointment(appointment);

        assertTrue(actual.isSuccess());
        assertEquals(expected, actual.getPayload());
    }

    @Test
    void shouldNotAddWithInvalidBusinessId() {
        Appointment appointment = makeAppointment(0);

        when(businessRepository.searchById(1)).thenReturn(null);
        Result<Appointment> actual = service.addAppointment(appointment);

        assertFalse(actual.isSuccess());
    }

    @Test
    void shouldNotAddWithInvalidServiceId() {
        Appointment appointment = makeAppointment(0);

        when(serviceRepository.getServiceById(1)).thenReturn(null);
        Result<Appointment> actual = service.addAppointment(appointment);

        assertFalse(actual.isSuccess());
    }

    @Test
    void shouldNotAddWithSetAppointmentId() {
        Appointment appointment = makeAppointment(1);
        Appointment expected = makeAppointment();

        when(availabilityRepository.searchByBusinessId(1)).thenReturn(makeAvailability());
        when(businessRepository.searchById(1)).thenReturn(makeBusiness());
        when(serviceRepository.getServiceById(1)).thenReturn(makeService());
        when(repository.addAppointment(appointment)).thenReturn(expected);

        Result<Appointment> actual = service.addAppointment(appointment);

        assertFalse(actual.isSuccess());
    }

    @Test
    void shouldNotAddWithZeroBusinessId() {
        Appointment appointment = makeAppointment(0);
        appointment.setBusinessId(0);

        Result<Appointment> actual = service.addAppointment(appointment);

        assertFalse(actual.isSuccess());
    }

    @Test
    void shouldNotAddPastDate() {
        Appointment appointment = makeAppointment(0);
        appointment.setAppointmentDateTime(LocalDateTime.of(2000, 3, 1, 12, 30));

        Result<Appointment> actual = service.addAppointment(appointment);

        assertFalse(actual.isSuccess());
    }

    @Test
    void shouldNotAddNullDate() {
        Appointment appointment = makeAppointment(0);
        appointment.setAppointmentDateTime(null);

        Result<Appointment> actual = service.addAppointment(appointment);

        assertFalse(actual.isSuccess());
    }

    @Test
    void shouldNotAddNull() {
        Result<Appointment> actual = service.addAppointment(null);

        assertFalse(actual.isSuccess());
    }

    @Test
    void shouldNotAddAppointmentConflictingWithAvailability() {
        Appointment appointment = makeAppointment(0);
        appointment.setAppointmentDateTime(LocalDateTime.of(2023, 12, 12, 19, 50));
        Appointment expected = makeAppointment();

        when(availabilityRepository.searchByBusinessId(1)).thenReturn(makeAvailability());
        when(businessRepository.searchById(1)).thenReturn(makeBusiness());
        when(serviceRepository.getServiceById(1)).thenReturn(makeService());
        when(repository.addAppointment(appointment)).thenReturn(expected);

        Result<Appointment> actual = service.addAppointment(appointment);

        assertFalse(actual.isSuccess());
    }

    @Test
    void shouldNotAddAppointmentStartConflictingWithExisting() {
        Appointment appointment = makeAppointment(0);
        appointment.setAppointmentDateTime(LocalDateTime.of(2023, 11, 11, 12, 45));
        Appointment expected = makeAppointment();

        when(repository.searchByBusinessId(1)).thenReturn(List.of(makeAppointment()));
        when(availabilityRepository.searchByBusinessId(1)).thenReturn(makeAvailability());
        when(businessRepository.searchById(1)).thenReturn(makeBusiness());
        when(serviceRepository.getServiceById(1)).thenReturn(makeService());
        when(repository.addAppointment(appointment)).thenReturn(expected);

        Result<Appointment> actual = service.addAppointment(appointment);

        assertFalse(actual.isSuccess());
    }

    @Test
    void shouldNotAddAppointmentEndConflictingWithExisting() {
        Appointment appointment = makeAppointment(0);
        appointment.setAppointmentDateTime(LocalDateTime.of(2023, 11, 11, 12, 15));
        Appointment expected = makeAppointment();

        when(repository.searchByBusinessId(1)).thenReturn(List.of(makeAppointment()));
        when(availabilityRepository.searchByBusinessId(1)).thenReturn(makeAvailability());
        when(businessRepository.searchById(1)).thenReturn(makeBusiness());
        when(serviceRepository.getServiceById(1)).thenReturn(makeService());
        when(repository.addAppointment(appointment)).thenReturn(expected);

        Result<Appointment> actual = service.addAppointment(appointment);

        assertFalse(actual.isSuccess());
    }

    private Appointment makeAppointment() {

        return new Appointment(
                1, 2, 1, 1,
                LocalDateTime.of(2023, 11, 11, 12, 30)
        );
    }

    private Appointment makeAppointment(int appointmentId) {

        return new Appointment(
                appointmentId, 2, 1, 1,
                LocalDateTime.of(2023, 11, 11, 12, 30)
        );
    }

    private Service makeService() {

        return new Service(1,1, "Service C", 30, new BigDecimal(90));
    }

    private Business makeBusiness() {

        return new Business(1, "Test", 1, Category.HAIR);
    }

    private Availability makeAvailability() {
        return new Availability(1,
                LocalTime.of(10, 0), LocalTime.of(20, 0),
                LocalTime.of(11, 0), LocalTime.of(12, 0),
                LocalTime.of(10, 0), LocalTime.of(20, 0),
                LocalTime.of(11, 0), LocalTime.of(12, 0),
                LocalTime.of(10, 0), LocalTime.of(20, 0),
                LocalTime.of(11, 0), LocalTime.of(12, 0),
                LocalTime.of(10, 0), LocalTime.of(20, 0),
                LocalTime.of(11, 0), LocalTime.of(12, 0),
                LocalTime.of(10, 0), LocalTime.of(20, 0),
                LocalTime.of(11, 0), LocalTime.of(12, 0),
                LocalTime.of(10, 0), LocalTime.of(20, 0),
                LocalTime.of(11, 0), LocalTime.of(12, 0),
                LocalTime.of(10, 0), LocalTime.of(20, 0),
                LocalTime.of(11, 0), LocalTime.of(12, 0)
        );
    }
}
