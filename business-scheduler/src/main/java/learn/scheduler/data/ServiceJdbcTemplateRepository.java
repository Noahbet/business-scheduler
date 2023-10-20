package learn.scheduler.data;

import learn.scheduler.data.mappers.ServiceMapper;
import learn.scheduler.models.Service;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

@Repository
public class ServiceJdbcTemplateRepository implements ServiceRepository{

    private final JdbcTemplate jdbcTemplate;

    public ServiceJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public List<Service> getServicesForBusiness(int businessId) {

        final String sql = "select service_id, business_id, service_name, total_service_length, cost "
                + "from service "
                + "where business_id = ?;";

        return jdbcTemplate.query(sql, new ServiceMapper(), businessId);
    }

    @Override
    @Transactional
    public Service getServiceById(int serviceId) {

        final String sql = "select service_id, business_id, service_name, total_service_length, cost "
                + "from service "
                + "where service_id = ?;";

        return jdbcTemplate.query(sql, new ServiceMapper(), serviceId)
                .stream().findFirst().orElse(null);
    }

    @Override
    @Transactional
    public Service addService(Service service) {

        final String sql = "insert into service "
                + "(service_name, business_id, total_service_length, cost) "
                + "values (?,?,?,?);";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, service.getServiceName());
            ps.setInt(2, service.getBusinessId());
            ps.setInt(3, service.getTotalTimeLength());
            ps.setBigDecimal(4, service.getCost());

            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        service.setServiceId(Objects.requireNonNull(keyHolder.getKey()).intValue());

        return service;
    }

    @Override
    public boolean updateService(Service updatedService) {

        final String sql = "update service set "
                + "service_name = ?"
                + "business_id = ?"
                + "total_service_length = ?"
                + "cost = ?"
                + "where business_name = ?;";

        return jdbcTemplate.update(sql,
                updatedService.getServiceName(), updatedService.getBusinessId(),
                updatedService.getTotalTimeLength(), updatedService.getCost(),
                updatedService.getServiceId()) > 0;
    }

    @Override
    public boolean deleteService(int serviceId) {

        final String sql = "delete from service where service_id = ?";
        return jdbcTemplate.update(sql, serviceId) > 0;
    }
}
