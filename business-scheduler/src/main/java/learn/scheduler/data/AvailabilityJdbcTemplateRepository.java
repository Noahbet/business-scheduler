package learn.scheduler.data;

import org.springframework.jdbc.core.JdbcTemplate;

public class AvailabilityJdbcTemplateRepository implements AvailabilityRepository{

    private final JdbcTemplate jdbcTemplate;

    public AvailabilityJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


}
