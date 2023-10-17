package learn.scheduler.data.mappers;

import learn.scheduler.models.Appointment;
import learn.scheduler.models.Availability;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class AvailabilityMapper implements RowMapper<Availability> {

    @Override
    public Availability mapRow(ResultSet rs, int i) throws SQLException {
        return new Availability(
                rs.getObject("monday_start", LocalTime.class),
                rs.getObject("monday_end", LocalTime.class),
                rs.getObject("monday_break_end", LocalTime.class),
                rs.getObject("monday_break_end", LocalTime.class),
    }
}
