package learn.scheduler.data.mappers;

import learn.scheduler.models.Notification;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NotificationMapper implements RowMapper<Notification> {

    @Override
    public Notification mapRow(ResultSet rs, int i) throws SQLException {
        return new Notification(
                rs.getInt("notification_id"),
                rs.getString("sender_email"),
                rs.getInt("receiver_id"),
                rs.getString("receiver_email"),
                rs.getString("message"));
    }
}
