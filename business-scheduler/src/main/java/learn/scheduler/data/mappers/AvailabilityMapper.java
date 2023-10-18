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
                rs.getObject("monday_break_start", LocalTime.class),
                rs.getObject("monday_break_end", LocalTime.class),
                rs.getObject("tuesday_start", LocalTime.class),
                rs.getObject("tuesday_end", LocalTime.class),
                rs.getObject("tuesday_break_start", LocalTime.class),
                rs.getObject("tuesday_break_end", LocalTime.class),
                rs.getObject("wednesday_start", LocalTime.class),
                rs.getObject("wednesday_end", LocalTime.class),
                rs.getObject("wednesday_break_start", LocalTime.class),
                rs.getObject("wednesday_break_end", LocalTime.class),
                rs.getObject("thursday_start", LocalTime.class),
                rs.getObject("thursday_end", LocalTime.class),
                rs.getObject("thursday_break_start", LocalTime.class),
                rs.getObject("thursday_break_end", LocalTime.class),
                rs.getObject("friday_start", LocalTime.class),
                rs.getObject("friday_end", LocalTime.class),
                rs.getObject("friday_break_start", LocalTime.class),
                rs.getObject("friday_break_end", LocalTime.class),
                rs.getObject("saturday_start", LocalTime.class),
                rs.getObject("saturday_end", LocalTime.class),
                rs.getObject("saturday_break_start", LocalTime.class),
                rs.getObject("saturday_break_end", LocalTime.class),
                rs.getObject("sunday_start", LocalTime.class),
                rs.getObject("sunday_end", LocalTime.class),
                rs.getObject("sunday_break_start", LocalTime.class),
                rs.getObject("sunday_break_end", LocalTime.class));
    }
}
