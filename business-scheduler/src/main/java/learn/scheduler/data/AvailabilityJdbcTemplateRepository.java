package learn.scheduler.data;

import learn.scheduler.data.mappers.AvailabilityMapper;
import learn.scheduler.models.Availability;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

public class AvailabilityJdbcTemplateRepository implements AvailabilityRepository{

    private final JdbcTemplate jdbcTemplate;

    public AvailabilityJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Availability searchByBusinessId(int businessId) {

        final String sql = "select monday_start, monday_end, monday_break_start, monday_break_end, "
                + "tuesday_start, tuesday_end, tuesday_break_start, tuesday_break_end, "
                + "wednesday_start, wednesday_end, wednesday_break_start, wednesday_break_end, "
                + "thursday_start, thursday_end, thursday_break_start, thursday_break_end, "
                + "friday_start, friday_end, friday_break_start, friday_break_end, "
                + "saturday_start, saturday_end, saturday_break_start, saturday_break_end, "
                + "sunday_start, sunday_end, sunday_break_start, sunday_break_end "
                + "from business_hours "
                + "where business_id = ?;";

        return jdbcTemplate.query(sql, new AvailabilityMapper(), businessId).stream()
                .findFirst().orElse(null);
    }
}
