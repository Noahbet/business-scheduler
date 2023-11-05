package learn.scheduler.data;

import org.springframework.transaction.annotation.Transactional;

public interface RatingRepository {

    @Transactional
    double getAvgRatingById(int businessId);

    int countRatingsForBusiness(int businessId);

    int addRating(int businessId, int userId, int rating);

    int updateRating(int businessId, int userId, int rating);
}
