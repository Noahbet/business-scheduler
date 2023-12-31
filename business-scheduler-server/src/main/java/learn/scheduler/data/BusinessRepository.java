package learn.scheduler.data;

import learn.scheduler.models.Business;
import learn.scheduler.models.Category;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BusinessRepository {

    @Transactional
    List<Business> searchBusinesses(String query);

    @Transactional
    List<Business> searchByCategory(Category category);

    @Transactional
    Business searchById(int businessId);

    @Transactional
    Business addBusiness(Business business);

    boolean updateBusinessName(int businessId, String updatedName);

    boolean deleteBusiness(int businessId);
}
