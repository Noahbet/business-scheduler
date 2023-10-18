package learn.scheduler.data;

import learn.scheduler.models.Availability;
import org.springframework.transaction.annotation.Transactional;

public interface AvailabilityRepository {
    @Transactional
    Availability searchByBusinessId(int businessId);
}
