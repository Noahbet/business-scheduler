package learn.scheduler.data;

import learn.scheduler.models.AppUser;

public interface AppUserRepository {
    AppUser findByUsername(String username);

    AppUser add(AppUser appUser);
}