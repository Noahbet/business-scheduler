package learn.scheduler.domain;

import learn.scheduler.data.AppUserRepository;
import learn.scheduler.data.AppointmentRepository;
import learn.scheduler.data.BusinessRepository;
import learn.scheduler.data.ServiceRepository;
import learn.scheduler.models.Appointment;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    
    private final BusinessRepository businessRepository;

    private final ServiceRepository serviceRepository;

    private final Validator validator;

    public AppointmentService(AppointmentRepository appointmentRepository, BusinessRepository businessRepository,
                              ServiceRepository serviceRepository, Validator validator) {
        this.appointmentRepository = appointmentRepository;
        this.businessRepository = businessRepository;
        this.serviceRepository = serviceRepository;
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
        }

        return result;
    }
}
