package com.oneupalbums.service;

import com.oneupalbums.model.ApplicationUser;

public interface ApplicationUserService {
    ApplicationUser findByUserName(String username);
}
