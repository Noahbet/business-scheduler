package learn.scheduler.domain;

import learn.scheduler.data.RatingJdbcTemplateRepository;
import learn.scheduler.models.Business;
import learn.scheduler.models.Category;

import java.util.List;

public class RatingService {

    private final RatingJdbcTemplateRepository repository;


    public RatingService(RatingJdbcTemplateRepository repository) {
        this.repository = repository;
    }

    public double getAvgRatingById(int businessId) {
        return repository.getAvgRatingById(businessId);
    }

    public int countRatingsForBusiness(int businessId) {
        return repository.countRatingsForBusiness(businessId);
    }

    public Result<Integer> addRating(int businessId, int userId, int rating) {

        Result<Integer> result = new Result<>();

        if (rating < 0) {
            result.addMessage(ActionStatus.INVALID, "Rating cannot be less than 0 for `add` operation");
            return result;
        }

        if (rating > 5) {
            result.addMessage(ActionStatus.INVALID, "Rating cannot be greater than 5 for `add` operation");
            return result;
        }

        int inserted = repository.addRating(businessId, userId, rating);

        return result;
    }

    public Result<Integer> updateBusinessName(int businessId, int userId, int rating) {

        Result<Integer> result = new Result<>();

        if (rating < 0) {
            result.addMessage(ActionStatus.INVALID, "Rating cannot be less than 0 for `add` operation");
            return result;
        }

        if (rating > 5) {
            result.addMessage(ActionStatus.INVALID, "Rating cannot be greater than 5 for `add` operation");
            return result;
        }

        int inserted = repository.addRating(businessId, userId, rating);

        return result;
    }

}
