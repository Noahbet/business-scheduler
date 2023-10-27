package learn.scheduler.domain;

import learn.scheduler.data.BusinessRepository;
import learn.scheduler.models.Business;
import learn.scheduler.models.Category;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class BusinessService {

    private final BusinessRepository repository;

    private final Validator validator;

    public BusinessService(BusinessRepository repository, Validator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    public List<Business> searchBusinesses(String query) {
        return repository.searchBusinesses(query);
    }

    public List<Business> searchByCategory(Category category) {
        return repository.searchByCategory(category);
    }

    public Business searchById(int businessId) {
        return repository.searchById(businessId);
    }

    public Result<Business> addBusiness(Business business) {

        Result<Business> result = validate(business);
        if (result.getStatus() != ActionStatus.SUCCESS) {
            return result;
        }

        if (business.getBusinessId() != 0) {
            result.addMessage(ActionStatus.INVALID, "BusinessId cannot be set for `add` operation");
            return result;
        }

        Business inserted = repository.addBusiness(business);
        if (inserted == null) {
            result.addMessage(ActionStatus.INVALID, "insert failed");
        } else {
            result.setPayload(inserted);
        }

        return result;
    }

    public Result<Business> updateBusinessName(Business business) {

        Result<Business> result = new Result<>();

        if (business == null) {
            result.addMessage(ActionStatus.INVALID, "Business cannot be null.");
            return result;
        }

        if (business.getBusinessId() == 0) {
            result.addMessage(ActionStatus.INVALID, "BusinessId must be set for `update` operation");
            return result;
        }

        if (!repository.updateBusinessName(business.getBusinessId(), business.getBusinessName())) {
            result.addMessage(ActionStatus.NOT_FOUND, "BusinessId `" + business.getBusinessId() + "` not found.");
        }

        return result;
    }

    public Result<Business> deleteBusiness(int businessId) {

        Result<Business> result = new Result<>();
        boolean deleted = repository.deleteBusiness(businessId);
        if (!deleted) {
            result.addMessage(ActionStatus.NOT_FOUND, "BusinessId `" + businessId + "` not found.");
        }
        return result;
    }

    private Result<Business> validate(Business business) {

        Result<Business> result = new Result<>();

        if (business == null) {
            result.addMessage(ActionStatus.INVALID, "Business cannot be null.");
            return result;
        }

        Set<ConstraintViolation<Business>> violations = validator.validate(business);

        for (ConstraintViolation<Business> violation : violations) {
            result.addMessage(ActionStatus.INVALID, violation.getMessage());
        }

        return result;
    }
}
