package learn.scheduler.data;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ServerJdbcTemplateRepository {

    private final JdbcTemplate jdbcTemplate;

    public ServerJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


}
