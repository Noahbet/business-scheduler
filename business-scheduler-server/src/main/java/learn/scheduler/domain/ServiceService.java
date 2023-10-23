package learn.scheduler.domain;

import learn.scheduler.data.BusinessRepository;
import learn.scheduler.data.ServiceRepository;
import learn.scheduler.models.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@org.springframework.stereotype.Service
public class ServiceService {

    private final ServiceRepository repository;
    private final BusinessRepository businessRepository;
    private final Validator validator;

    public ServiceService(ServiceRepository repository, BusinessRepository businessRepository, Validator validator) {
        this.repository = repository;
        this.businessRepository = businessRepository;
        this.validator = validator;
    }

    public List<Service> getServicesForBusiness(int businessId) {
        return repository.getServicesForBusiness(businessId);
    }

    public Service getServiceById(int serviceId) {
        return repository.getServiceById(serviceId);
    }

    public Result<Service> addService(Service service) {
        Result<Service> result = validate(service);
        if (!result.isSuccess()) {
            return result;
        }

        if (service.getServiceId() != 0) {
            result.addMessage(ActionStatus.INVALID, "AppointmentId cannot be set for `add` operation");
            return result;
        }

        service = repository.addService(service);
        result.setPayload(service);
        return result;
    }

    public Result<Service> updateService(Service service) {

        Result<Service> result = validate(service);
        if (result.getStatus() != ActionStatus.SUCCESS) {
            return result;
        }

        if (!repository.updateService(service)) {
            result.addMessage(ActionStatus.NOT_FOUND, "ServiceId `" + service.getServiceId() + "` not found.");
        }

        return result;
    }

    public Result<Service> deleteService(int serviceId) {
        Result<Service> result = new Result<>();
        boolean deleted = repository.deleteService(serviceId);
        if (!deleted) {
            result.addMessage(ActionStatus.NOT_FOUND, "ServiceId `" + serviceId + "` not found.");
        }
        return result;
    }

    private Result<Service> validate(Service service) {

        Result<Service> result = new Result<>();

        if (service == null) {
            result.addMessage(ActionStatus.INVALID, "Service cannot be null.");
            return result;
        }

        Set<ConstraintViolation<Service>> violations = validator.validate(service);

        for (ConstraintViolation<Service> violation : violations) {
            result.addMessage(ActionStatus.INVALID, violation.getMessage());
            return result;
        }

        if (businessRepository.searchById(service.getBusinessId()) == null) {
            result.addMessage(ActionStatus.NOT_FOUND, "Business with " + service.getBusinessId() + " not found.");
        }

        return result;
    }
}
