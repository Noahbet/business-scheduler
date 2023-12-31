package learn.scheduler.data;

import learn.scheduler.data.mappers.RatingMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class RatingJdbcTemplateRepository implements RatingRepository{

    private final JdbcTemplate jdbcTemplate;

    public RatingJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public double getAvgRatingById(int businessId) {
        final String sql = "select avg(rating_value) rating_value "
               + "from rating "
               + "where business_id = ?";

        return jdbcTemplate.query(sql,
                new RatingMapper(), businessId).stream()
                .findFirst().orElse(0.0);
    }

    @Override
    public int countRatingsForBusiness(int businessId) {
        final String sql = "select count(rating_value) "
                + "from rating "
                + "where business_id = ?";

        return jdbcTemplate.queryForObject(sql, Integer.class, businessId).intValue();
    }

    @Override
    public int addRating(int businessId, int userId, int rating) {

        final String sql = "insert into rating "
                + "(business_id, app_user_id, rating_value) "
                + "values (?,?,?);";

        return jdbcTemplate.update(sql, businessId, userId, (double) rating);
    }

    @Override
    public int updateRating(int businessId, int userId, int rating) {

        final String sql = "update rating set "
                + "rating_value = ? "
                + "where business_id = ? "
                + "and app_user_id = ?;";

        return jdbcTemplate.update(sql, (double) rating, businessId, userId);
    }
}
