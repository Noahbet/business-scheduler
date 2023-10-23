package learn.scheduler.data;

import learn.scheduler.models.Appointment;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AppointmentRepository {
    @Transactional
    List<Appointment> searchByUserId(int userId);

    @Transactional
    List<Appointment> searchByBusinessId(int businessId);

    @Transactional
    Appointment addAppointment(Appointment appointment);

    boolean deleteAppointment(int appointmentId);
}
