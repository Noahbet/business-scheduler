package learn.scheduler.data.mappers;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RatingMapper implements RowMapper<Double> {

    @Override
    public Double mapRow(ResultSet rs, int i) throws SQLException {
        return rs.getObject("rating_value", Double.class);
    }
}
