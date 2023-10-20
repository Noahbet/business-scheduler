package learn.scheduler.data;

import learn.scheduler.models.AppUser;
import org.springframework.transaction.annotation.Transactional;

public interface AppUserRepository {
    @Transactional
    AppUser findByEmail(String email);

    @Transactional
    AppUser create(AppUser user);

    @Transactional
    boolean update(AppUser user);
}
