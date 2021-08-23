package com.oneupalbums.service.impl;

import com.oneupalbums.model.ApplicationUser;
import com.oneupalbums.repository.ApplicationUserRepository;
import com.oneupalbums.service.ApplicationUserService;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserServiceImpl implements ApplicationUserService {

    // auto-wired
    private final ApplicationUserRepository applicationUserRepository;

    public ApplicationUserServiceImpl(ApplicationUserRepository applicationUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
    }

    @Override
    public ApplicationUser findByUserName(String username) {
        return applicationUserRepository.findByUserName(username);
    }
}
