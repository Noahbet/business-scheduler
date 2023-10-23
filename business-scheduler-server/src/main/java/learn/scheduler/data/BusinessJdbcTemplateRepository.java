package learn.scheduler.data;

import learn.scheduler.data.mappers.BusinessMapper;
import learn.scheduler.models.Business;
import learn.scheduler.models.Category;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

@Repository
public class BusinessJdbcTemplateRepository implements BusinessRepository{

    private final JdbcTemplate jdbcTemplate;

    public BusinessJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public List<Business> searchBusinesses(String query) {

        query = "%" + query + "%";

        final String sql = "select b.business_id, b.business_name, b.owner_id, bc.category "
                + "from business b "
                + "inner join business_category bc on b.category_id = bc.category_id "
                + "where b.business_name like ?;";

        return jdbcTemplate.query(sql, new BusinessMapper(), query);
    }

    @Override
    @Transactional
    public List<Business> searchByCategory(Category category) {

        String categoryString = String.valueOf(category);

        final String sql = "select b.business_id, b.business_name, b.owner_id, bc.category "
                + "from business b "
                + "inner join business_category bc on b.category_id = bc.category_id "
                + "where bc.category = ?;";

        return jdbcTemplate.query(sql, new BusinessMapper(), categoryString);
    }

    @Override
    @Transactional
    public Business searchById(int businessId) {

        final String sql = "select b.business_id, b.business_name, b.owner_id, bc.category "
                + "from business b "
                + "inner join business_category bc on b.category_id = bc.category_id "
                + "where b.business_id = ?;";

        return jdbcTemplate.query(sql, new BusinessMapper(), businessId)
                .stream().findFirst().orElse(null);
    }

    @Override
    @Transactional
    public Business addBusiness(Business business) {

        final String sql = "insert into business "
                + "(business_name, owner_id, category_id) "
                + "values (?,?,?);";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, business.getBusinessName());
            ps.setInt(2, business.getOwnerId());
            ps.setInt(3, business.getCategory().getCategoryId());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        business.setBusinessId(Objects.requireNonNull(keyHolder.getKey()).intValue());

        return business;
    }

    @Override
    public boolean updateBusinessName(int businessId, String updatedName) {

        final String sql = "update business set "
                + "business_name = ?"
                + "where business_id = ?;";

        return jdbcTemplate.update(sql,
                updatedName,
                businessId) > 0;
    }

    @Override
    public boolean deleteBusiness(int businessId) {

        final String sql = "delete from business where business_id = ?";
        return jdbcTemplate.update(sql, businessId) > 0;
    }
}
