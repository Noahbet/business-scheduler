package learn.scheduler.data.mappers;

import learn.scheduler.models.Service;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceMapper implements RowMapper<Service> {

    @Override
    public Service mapRow(ResultSet rs, int i) throws SQLException {
        return new Service(
                rs.getInt("service_id"),
                rs.getString("service_name"),
                rs.getInt("total_service_length"),
                rs.getBigDecimal("cost"));
    }
}
