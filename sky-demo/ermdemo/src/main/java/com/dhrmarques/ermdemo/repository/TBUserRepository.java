package com.dhrmarques.ermdemo.repository;

import com.dhrmarques.ermdemo.model.TBUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TBUserRepository extends JpaRepository<TBUser, Long> {

    Optional<TBUser> findByEmail(String email);

}
