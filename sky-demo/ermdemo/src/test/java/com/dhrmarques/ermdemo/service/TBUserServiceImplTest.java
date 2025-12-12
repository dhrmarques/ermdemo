package com.dhrmarques.ermdemo.service;

import com.dhrmarques.ermdemo.model.TBUser;
import com.dhrmarques.ermdemo.repository.TBUserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TBUserServiceImplTest {

    private TBUserRepository repository;

    private PasswordEncoder encoder;

    private TBUserServiceImpl service;

    @BeforeEach
    void setUp() {
        repository = mock(TBUserRepository.class);
        encoder = mock(BCryptPasswordEncoder.class);

        service = new TBUserServiceImpl(repository, encoder);
    }

    @Test
    void testSave() {
        TBUser user = mock(TBUser.class);
        when(user.getName()).thenReturn("mockName");
        when(user.getEmail()).thenReturn("mockEmail");
        when(user.getPassword()).thenReturn("mockPassword");

        when(encoder.encode("mockPassword")).thenReturn("encodedPassword");
        when(repository.save(any(TBUser.class))).thenReturn(user);

        TBUser saved = service.save(user);
        Assertions.assertEquals("mockName", saved.getName());
        Assertions.assertEquals("mockEmail", saved.getEmail());
    }

    @Test
    void testFindAllUsers() {
        when(repository.findAll()).thenReturn(List.of());

        List<TBUser> users = service.findAllUsers();
        Assertions.assertEquals(0, users.size());
    }

    @Test
    void testFindById() {
        when(repository.findById(1L)).thenReturn(Optional.of(mock(TBUser.class)));

        Optional<TBUser> user = service.findById(1L);
        Assertions.assertTrue(user.isPresent());
    }

    @Test
    void testFindByEmail() {
        when(repository.findByEmail("mockEmail")).thenReturn(Optional.of(mock(TBUser.class)));

        Optional<TBUser> user = service.findByEmail("mockEmail");
        Assertions.assertTrue(user.isPresent());
    }

    @Test
    void testUpdate() {
        TBUser user = mock(TBUser.class);
        when(user.getName()).thenReturn("mockName");
        when(user.getEmail()).thenReturn("mockEmail");
        when(user.getPassword()).thenReturn("mockPassword");

        when(encoder.encode("mockPassword")).thenReturn("encodedPassword");
        when(repository.save(any(TBUser.class))).thenReturn(user);

        TBUser saved = service.updateUser(user);
        Assertions.assertEquals("mockName", saved.getName());
        Assertions.assertEquals("mockEmail", saved.getEmail());
    }

    @Test
    void testDelete() {
        doNothing().when(repository).deleteById(1L);

        Assertions.assertDoesNotThrow(() -> service.deleteUser(1L));
    }

}
