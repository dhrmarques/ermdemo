package com.dhrmarques.ermdemo.service;

import com.dhrmarques.ermdemo.model.TBUser;

import java.util.List;
import java.util.Optional;

public interface TBUserService {

    TBUser save(TBUser user);

    List<TBUser> findAllUsers();

    Optional<TBUser> findById(Long userId);

    TBUser updateUser(TBUser user);

    void deleteUser(Long userId);

    Optional<TBUser> findByEmail(String email);
}
