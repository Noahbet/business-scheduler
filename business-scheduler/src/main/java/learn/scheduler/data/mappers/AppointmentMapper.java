package learn.scheduler.data.mappers;

import learn.scheduler.models.Appointment;
import learn.scheduler.models.Service;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class AppointmentMapper implements RowMapper<Appointment> {

    @Override
    public Appointment mapRow(ResultSet rs, int i) throws SQLException {

        return new Appointment(
                rs.getInt("appointment_id"),
                rs.getInt("customer_id"),
                rs.getInt("business_id"),
                rs.getInt("service_id"),
                rs.getObject("date_time", LocalDateTime.class));
    }
}
