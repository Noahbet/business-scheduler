package learn.scheduler.data;

import learn.scheduler.data.mappers.AppointmentMapper;
import learn.scheduler.models.Appointment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

public class AppointmentJdbcTemplateRepository implements AppointmentRepository{

    private final JdbcTemplate jdbcTemplate;

    public AppointmentJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public List<Appointment> searchByUserId(int userId) {

        final String sql = "select a.appointment_id, a.customer_id, a.business_id, a.date_time, "
                + "s.service_id, s.service_name, s.total_service_length, s.cost "
                + "from appointment a "
                + "inner join service s on a.service_id = s.service_id "
                + "where a.customer_id = ?;";

        return jdbcTemplate.query(sql, new AppointmentMapper(), userId);
    }

    @Override
    @Transactional
    public List<Appointment> searchByBusinessId(int businessId) {

        final String sql = "select a.appointment_id, a.customer_id, a.business_id, a.date_time, "
                + "s.service_id, s.service_name, s.total_service_length, s.cost "
                + "from appointment a "
                + "inner join service s on a.service_id = s.service_id "
                + "where a.business_id = ?;";

        return jdbcTemplate.query(sql, new AppointmentMapper(), businessId);
    }

    @Override
    @Transactional
    public Appointment addAppointment(Appointment appointment) {

        final String sql = "insert into appointment "
                + "(customer_id, business_id, service_id, date_time) "
                + "values (?,?,?,?);";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, appointment.getCustomerId());
            ps.setInt(2, appointment.getBusinessId());
            ps.setInt(3, appointment.getService().getServiceId());
            ps.setObject(4, appointment.getAppointmentDateTime());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        appointment.setAppointmentId(Objects.requireNonNull(keyHolder.getKey()).intValue());

        return appointment;
    }

    @Override
    public boolean deleteAppointment(int appointmentId) {

        final String sql = "delete from appointment where appointment_id = ?";
        return jdbcTemplate.update(sql, appointmentId) > 0;
    }
}
