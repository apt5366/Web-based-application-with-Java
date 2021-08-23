package com.oneupalbums.repository.impl;

import com.oneupalbums.jpa.JpaApplicationUserRepository;
import com.oneupalbums.model.ApplicationUser;
import com.oneupalbums.repository.ApplicationUserRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ApplicationUserRepositoryImpl implements ApplicationUserRepository {

    // autowired
    private final JpaApplicationUserRepository jpaApplicationUserRepository;

    public ApplicationUserRepositoryImpl(JpaApplicationUserRepository jpaApplicationUserRepository) {
        this.jpaApplicationUserRepository = jpaApplicationUserRepository;
    }

    @Override
    public ApplicationUser addUser(ApplicationUser user) {
        return jpaApplicationUserRepository.save(user);
    }

    @Override
    public ApplicationUser findByUserName(String username) {
        return jpaApplicationUserRepository.findByUsername(username);
    }
}
