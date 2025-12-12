package com.dhrmarques.ermdemo.service;

import com.dhrmarques.ermdemo.model.TBUser;
import com.dhrmarques.ermdemo.repository.TBUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TBUserServiceImpl implements TBUserService {

    private final TBUserRepository repository;

    private final PasswordEncoder encoder;

    @Autowired
    public TBUserServiceImpl(TBUserRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @Override
    public TBUser save(TBUser user) {
        TBUser userToSave = new TBUser();
        userToSave.setEmail(user.getEmail());
        userToSave.setName(user.getName());
        userToSave.setPassword(encoder.encode(user.getPassword()));

        return repository.save(userToSave);
    }

    @Override
    public List<TBUser> findAllUsers() {
        return repository.findAll();
    }

    @Override
    public Optional<TBUser> findById(Long userId) {
        return repository.findById(userId);
    }

    @Override
    public Optional<TBUser> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public TBUser updateUser(TBUser user) {
        TBUser userToSave = new TBUser();
        userToSave.setEmail(user.getEmail());
        userToSave.setName(user.getName());

        if (user.getPassword() != null && !user.getPassword().isBlank()) {
            userToSave.setPassword(encoder.encode(user.getPassword()));
        }

        return repository.save(userToSave);
    }

    @Override
    public void deleteUser(Long userId) {
        repository.deleteById(userId);
    }
}
