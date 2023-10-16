package learn.scheduler.data.mappers;

import learn.scheduler.models.Business;
import learn.scheduler.models.Category;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BusinessMapper implements RowMapper<Business> {

    @Override
    public Business mapRow(ResultSet rs, int i) throws SQLException {
        return new Business(
                rs.getInt("business_id"),
                rs.getString("business_name"),
                rs.getInt("owner_id"),
                Category.valueOf(rs.getString("category")));
    }
}