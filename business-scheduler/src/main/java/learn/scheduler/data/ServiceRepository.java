package learn.scheduler.data;

import learn.scheduler.models.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ServiceRepository {
    @Transactional
    List<Service> getServicesForBusiness(int businessId);

    @Transactional
    Service getServiceById(int serviceId);

    @Transactional
    Service addService(Service service);

    boolean updateService(Service updatedService);

    boolean deleteService(int serviceId);
}
