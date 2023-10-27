package learn.scheduler.data;

import learn.scheduler.data.mappers.NotificationMapper;
import learn.scheduler.models.Notification;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

@Repository
public class NotificationJdbcTemplateRepository implements NotificationRepository{

    private final JdbcTemplate jdbcTemplate;

    public NotificationJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public List<Notification> getMessagesByUserId(int userId) {

        final String sql = "select n.notification_id, n.receiver_id, n.message, "
                + "us.username as sender_email, ur.username as receiver_email "
                + "from notification n "
                + "left join app_user us on n.sender_id = us.app_user_id "
                + "left join app_user ur on n.receiver_id = us.app_user_id "
                + "where n.receiver_id = ?;";

        return jdbcTemplate.query(sql, new NotificationMapper(), userId);
    }

    @Override
    @Transactional
    public Notification addNotification(Notification notification) {

        final String sql = "insert into notification "
                + "(message, sender_id, receiver_id) "
                + "values (?,?,?);";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, notification.getMessage());
            ps.setInt(2, notification.getSenderId());
            ps.setInt(3, notification.getReceiverId());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        notification.setNotificationId(Objects.requireNonNull(keyHolder.getKey()).intValue());

        return notification;
    }

    @Override
    public boolean deleteNotification(int notificationId) {

        final String sql = "delete from notification where notification_id = ?";
        return jdbcTemplate.update(sql, notificationId) > 0;
    }
}
