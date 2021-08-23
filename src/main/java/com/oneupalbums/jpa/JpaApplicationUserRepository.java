package com.oneupalbums.jpa;

import com.oneupalbums.model.ApplicationUser;
import org.springframework.data.repository.CrudRepository;

public interface JpaApplicationUserRepository extends CrudRepository<ApplicationUser, Long> {
    ApplicationUser findByUsername(String username);
}
