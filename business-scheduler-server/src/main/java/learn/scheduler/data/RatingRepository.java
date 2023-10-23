package learn.scheduler.data;

import org.springframework.transaction.annotation.Transactional;

public interface RatingRepository {

    @Transactional
    double getAvgRatingById(int businessId);

    int countRatingsForBusiness(int businessId);

    boolean addRating(int businessId, int userId, int rating);

    boolean updateRating(int businessId, int userId, int rating);
}
