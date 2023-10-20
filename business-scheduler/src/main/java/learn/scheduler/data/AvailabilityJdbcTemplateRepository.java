package learn.scheduler.data;

import learn.scheduler.data.mappers.AvailabilityMapper;
import learn.scheduler.models.Availability;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Time;
import java.util.Objects;

@Repository
public class AvailabilityJdbcTemplateRepository implements AvailabilityRepository{

    private final JdbcTemplate jdbcTemplate;

    public AvailabilityJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Availability searchByBusinessId(int businessId) {

        final String sql = "select business_id, monday_start, monday_end, monday_break_start, monday_break_end, "
                + "tuesday_start, tuesday_end, tuesday_break_start, tuesday_break_end, "
                + "wednesday_start, wednesday_end, wednesday_break_start, wednesday_break_end, "
                + "thursday_start, thursday_end, thursday_break_start, thursday_break_end, "
                + "friday_start, friday_end, friday_break_start, friday_break_end, "
                + "saturday_start, saturday_end, saturday_break_start, saturday_break_end, "
                + "sunday_start, sunday_end, sunday_break_start, sunday_break_end "
                + "from availability "
                + "where business_id = ?;";

        return jdbcTemplate.query(sql, new AvailabilityMapper(), businessId).stream()
                .findFirst().orElse(null);
    }

    @Override
    @Transactional
    public Availability addAvailability(Availability availability) {

        final String sql = "insert into availability "
                + "(business_id, monday_start, monday_end, monday_break_start, monday_break_end, "
                + "tuesday_start, tuesday_end, tuesday_break_start, tuesday_break_end, "
                + "wednesday_start, wednesday_end, wednesday_break_start, wednesday_break_end, "
                + "thursday_start, thursday_end, thursday_break_start, thursday_break_end, "
                + "friday_start, friday_end, friday_break_start, friday_break_end, "
                + "saturday_start, saturday_end, saturday_break_start, saturday_break_end, "
                + "sunday_start, sunday_end, sunday_break_start, sunday_break_end) "
                + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, availability.getBusinessId());
            ps.setTime(2, Time.valueOf(availability.getMondayStart()));
            ps.setTime(3, Time.valueOf(availability.getMondayEnd()));
            ps.setTime(4, Time.valueOf(availability.getMondayBreakStart()));
            ps.setTime(5, Time.valueOf(availability.getMondayBreakEnd()));
            ps.setTime(6, Time.valueOf(availability.getTuesdayStart()));
            ps.setTime(7, Time.valueOf(availability.getTuesdayEnd()));
            ps.setTime(8, Time.valueOf(availability.getTuesdayBreakStart()));
            ps.setTime(9, Time.valueOf(availability.getTuesdayBreakEnd()));
            ps.setTime(10, Time.valueOf(availability.getWednesdayStart()));
            ps.setTime(11, Time.valueOf(availability.getWednesdayEnd()));
            ps.setTime(12, Time.valueOf(availability.getWednesdayBreakStart()));
            ps.setTime(13, Time.valueOf(availability.getWednesdayBreakEnd()));
            ps.setTime(14, Time.valueOf(availability.getThursdayStart()));
            ps.setTime(15, Time.valueOf(availability.getThursdayEnd()));
            ps.setTime(16, Time.valueOf(availability.getThursdayBreakStart()));
            ps.setTime(17, Time.valueOf(availability.getThursdayBreakEnd()));
            ps.setTime(18, Time.valueOf(availability.getFridayStart()));
            ps.setTime(19, Time.valueOf(availability.getFridayEnd()));
            ps.setTime(20, Time.valueOf(availability.getFridayBreakStart()));
            ps.setTime(21, Time.valueOf(availability.getFridayBreakEnd()));
            ps.setTime(22, Time.valueOf(availability.getSaturdayStart()));
            ps.setTime(23, Time.valueOf(availability.getSaturdayEnd()));
            ps.setTime(24, Time.valueOf(availability.getSaturdayBreakStart()));
            ps.setTime(25, Time.valueOf(availability.getSaturdayBreakEnd()));
            ps.setTime(26, Time.valueOf(availability.getSundayStart()));
            ps.setTime(27, Time.valueOf(availability.getSundayEnd()));
            ps.setTime(28, Time.valueOf(availability.getSundayBreakStart()));
            ps.setTime(29, Time.valueOf(availability.getSundayBreakEnd()));
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        availability.setAvailabilityId(keyHolder.getKey().intValue());

        return availability;
    }

    @Override
    public boolean updateAvailability(Availability availability) {

        final String sql = "update availability set "
                + "monday_start = ?, monday_end = ?, monday_break_start = ?, monday_break_end = ?, "
                + "tuesday_start = ?, tuesday_end = ?, tuesday_break_start = ?, tuesday_break_end = ?, "
                + "wednesday_start = ?, wednesday_end = ?, wednesday_break_start = ?, wednesday_break_end = ?, "
                + "thursday_start = ?, thursday_end = ?, thursday_break_start = ?, thursday_break_end = ?, "
                + "friday_start = ?, friday_end = ?, friday_break_start = ?, friday_break_end = ?, "
                + "saturday_start = ?, saturday_end = ?, saturday_break_start = ?, saturday_break_end = ?, "
                + "sunday_start = ?, sunday_end = ?, sunday_break_start = ?, sunday_break_end = ? "
                + "where business_id = ?;";

        return jdbcTemplate.update(sql,
                availability.getMondayStart(), availability.getMondayEnd(),
                availability.getMondayBreakStart(), availability.getMondayBreakEnd(),
                availability.getTuesdayStart(), availability.getTuesdayEnd(),
                availability.getTuesdayBreakStart(), availability.getTuesdayBreakEnd(),
                availability.getWednesdayStart(), availability.getWednesdayEnd(),
                availability.getWednesdayBreakStart(), availability.getWednesdayBreakEnd(),
                availability.getThursdayStart(), availability.getThursdayEnd(),
                availability.getThursdayBreakStart(), availability.getThursdayBreakEnd(),
                availability.getFridayStart(), availability.getFridayEnd(),
                availability.getFridayBreakStart(), availability.getFridayBreakEnd(),
                availability.getSaturdayStart(), availability.getSaturdayEnd(),
                availability.getSaturdayBreakStart(), availability.getSaturdayBreakEnd(),
                availability.getSundayStart(), availability.getSundayEnd(),
                availability.getSundayBreakStart(), availability.getSundayBreakEnd(),
                availability.getBusinessId()) > 0;
    }
}
