package com.oneupalbums.repository;

import com.oneupalbums.model.ApplicationUser;

public interface ApplicationUserRepository {
    ApplicationUser addUser(ApplicationUser user);

    ApplicationUser findByUserName(String username);
}
