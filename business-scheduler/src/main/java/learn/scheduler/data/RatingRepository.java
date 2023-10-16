package learn.scheduler.data;

import learn.scheduler.models.Business;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RatingRepository {

    @Transactional
    double getAvgRatingById(int businessId);

    double countRatingsForBusiness(int businessId);

    boolean addRating(int businessId, int userId, int rating);

    boolean updateRating(int businessId, int userId, int rating);
}
