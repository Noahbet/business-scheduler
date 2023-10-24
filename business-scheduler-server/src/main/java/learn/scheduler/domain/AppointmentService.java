package learn.scheduler.domain;

import learn.scheduler.data.*;
import learn.scheduler.models.Appointment;
import learn.scheduler.models.Availability;
import learn.scheduler.models.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

@org.springframework.stereotype.Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final BusinessRepository businessRepository;
    private final ServiceRepository serviceRepository;
    private final AvailabilityRepository availabilityRepository;
    private final Validator validator;

    public AppointmentService(AppointmentRepository appointmentRepository, BusinessRepository businessRepository,
                              ServiceRepository serviceRepository, AvailabilityRepository availabilityRepository,
                              Validator validator) {
        this.appointmentRepository = appointmentRepository;
        this.businessRepository = businessRepository;
        this.serviceRepository = serviceRepository;
        this.availabilityRepository = availabilityRepository;
        this.validator = validator;
    }

    public List<Appointment> searchByUserId(int userId) {
        return appointmentRepository.searchByUserId(userId);
    }

    public List<Appointment> searchByBusinessId(int businessId) {
        return appointmentRepository.searchByBusinessId(businessId);
    }

    public Result<Appointment> addAppointment(Appointment appointment) {
        Result<Appointment> result = validate(appointment);
        if (!result.isSuccess()) {
            return result;
        }

        if (appointment.getAppointmentId() != 0) {
            result.addMessage(ActionStatus.INVALID, "AppointmentId cannot be set for `add` operation");
            return result;
        }

        appointment = appointmentRepository.addAppointment(appointment);
        result.setPayload(appointment);
        return result;
    }

    public Result<Appointment> deleteAppointment(int appointmentId) {
        Result<Appointment> result = new Result<>();
        boolean deleted = appointmentRepository.deleteAppointment(appointmentId);
        if (!deleted) {
            result.addMessage(ActionStatus.NOT_FOUND, "AppointmentId `" + appointmentId + "` not found.");
        }
        return result;
    }

    private Result<Appointment> validate(Appointment appointment) {

        Result<Appointment> result = new Result<>();

        if (appointment == null) {
            result.addMessage(ActionStatus.INVALID, "Appointment cannot be null.");
            return result;
        }

        Set<ConstraintViolation<Appointment>> violations = validator.validate(appointment);

        for (ConstraintViolation<Appointment> violation : violations) {
            result.addMessage(ActionStatus.INVALID, violation.getMessage());
            return result;
        }

        if (businessRepository.searchById(appointment.getBusinessId()) == null) {
            result.addMessage(ActionStatus.NOT_FOUND, "Business with " + appointment.getBusinessId() + " not found.");
            return result;
        }

        if (serviceRepository.getServiceById(appointment.getServiceId()) == null) {
            result.addMessage(ActionStatus.NOT_FOUND, "Service with " + appointment.getBusinessId() + " not found.");
            return result;
        }

        result = doesNotConflictWithAvailability(result, appointment);

        if (!result.isSuccess()) {
            return result;
        }

        result = doesNotConflictWithExistingAppointments(result, appointment);
        return result;
    }

    private Result<Appointment> doesNotConflictWithAvailability(Result<Appointment> result, Appointment appointment) {

        LocalTime appointmentTime = LocalTime.of(appointment.getAppointmentDateTime().getHour(),
                appointment.getAppointmentDateTime().getMinute());
        Service service = serviceRepository.getServiceById(appointment.getServiceId());

        Availability availability = availabilityRepository.searchByBusinessId(appointment.getBusinessId());
        List<LocalTime> dayAvailability = availability.getAvailabilityByDay(appointment.getAppointmentDateTime().getDayOfWeek());

        if (appointmentTime.isBefore(dayAvailability.get(0))) {
            result.addMessage(ActionStatus.INVALID, "Appointment starts is before the business opens for the day.");
            return result;
        }

        if (appointmentTime.plusMinutes(service.getTotalTimeLength()).isAfter(dayAvailability.get(1))) {
            result.addMessage(ActionStatus.INVALID, "Appointment ends is after the business closes for the day.");
            return result;
        }

        if (appointmentTime.isAfter(dayAvailability.get(2)) &&
                        appointmentTime.isBefore(dayAvailability.get(3))) {
            result.addMessage(ActionStatus.INVALID, "Appointment starts during the scheduled break time for business.");
            return result;
        }

        if (appointmentTime.plusMinutes(service.getTotalTimeLength()).isAfter(dayAvailability.get(2)) &&
                appointmentTime.plusMinutes(service.getTotalTimeLength()).isBefore(dayAvailability.get(3))) {
            result.addMessage(ActionStatus.INVALID, "Appointment ends during the scheduled break time for business.");
        }

        return result;
    }

    private Result<Appointment> doesNotConflictWithExistingAppointments(Result<Appointment> result, Appointment appointment) {

        Service service = serviceRepository.getServiceById(appointment.getServiceId());
        LocalTime appointmentStartTime = LocalTime.of(appointment.getAppointmentDateTime().getHour(),
                appointment.getAppointmentDateTime().getMinute());
        LocalTime appointmentEndTime = appointmentStartTime.plusMinutes(service.getTotalTimeLength());

        List<LocalTime> appointmentStartTimes = makeStartLocalTimeList(appointment);

        List<LocalTime> appointmentEndTimes = makeEndLocalTimeList(appointment);

        for (int i = 0; i < appointmentStartTimes.size(); i++) {
            if (appointmentStartTime.isAfter(appointmentStartTimes.get(i)) &&
                    appointmentStartTime.isBefore(appointmentEndTimes.get(i))) {
                result.addMessage(ActionStatus.INVALID, "AppointmentStart conflicts with existing appointment.");
                return result;
            }

            if (appointmentEndTime.isAfter(appointmentStartTimes.get(i)) &&
                    appointmentStartTime.isBefore(appointmentEndTimes.get(i))) {
                result.addMessage(ActionStatus.INVALID, "AppointmentEnd conflicts with existing appointment.");
                return result;
            }

            if ((appointmentStartTimes.get(i).isAfter(appointmentStartTime) &&
                    appointmentStartTimes.get(i).isBefore(appointmentEndTime) &&
                    (appointmentStartTimes.get(i).isAfter(appointmentStartTime) &&
                    appointmentStartTimes.get(i).isBefore(appointmentEndTime)))) {
                result.addMessage(ActionStatus.INVALID, "Appointment surrounds existing appointment.");
                return result;
            }
        }

        return result;
    }

    private Stream<Appointment> makeAppointmentsStream(Appointment appointment) {

        int year = appointment.getAppointmentDateTime().getYear();
        int month = appointment.getAppointmentDateTime().getMonthValue();
        int day = appointment.getAppointmentDateTime().getDayOfMonth();

        return appointmentRepository.searchByBusinessId(appointment.getBusinessId())
                .stream().filter(app -> app.getAppointmentDateTime().getYear() == year &&
                        app.getAppointmentDateTime().getMonthValue() == month &&
                        app.getAppointmentDateTime().getDayOfMonth() == day);
    }

    private List<LocalTime> makeStartLocalTimeList(Appointment appointment) {

        Stream<Appointment> appointments = makeAppointmentsStream(appointment);

        return appointments.map(app -> LocalTime.of(app.getAppointmentDateTime().getHour(),
                app.getAppointmentDateTime().getMinute())).toList();
        }

    private List<LocalTime> makeEndLocalTimeList(Appointment appointment) {

        Stream<Appointment> appointments = makeAppointmentsStream(appointment);

        return appointments.map(app -> LocalTime.of(
                app.getAppointmentDateTime().plusMinutes(serviceRepository
                        .getServiceById(appointment.getServiceId()).getTotalTimeLength()).getHour(),
                app.getAppointmentDateTime().plusMinutes(serviceRepository
                        .getServiceById(appointment.getServiceId()).getTotalTimeLength()).getMinute())).toList();
    }
}
