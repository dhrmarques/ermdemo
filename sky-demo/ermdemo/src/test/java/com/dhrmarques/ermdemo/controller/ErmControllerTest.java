package com.dhrmarques.ermdemo.controller;

import com.dhrmarques.ermdemo.model.SecurityTBUser;
import com.dhrmarques.ermdemo.model.TBUser;
import com.dhrmarques.ermdemo.service.TBUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

class ErmControllerTest {

    private TBUserService service;

    private Authentication authentication;

    private Model model;

    private ErmController controller;

    @BeforeEach
    void setUp() {
        service = mock(TBUserService.class);
        model = mock(Model.class);
        controller = new ErmController(service);

        SecurityContext context = mock(SecurityContext.class);
        authentication = mock(Authentication.class);
        when(context.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(context);
    }

    @Test
    void testStartingPage() {
        SecurityTBUser mockSecurity = mock(SecurityTBUser.class);
        when(authentication.getPrincipal()).thenReturn(mockSecurity);

        TBUser user = mock(TBUser.class);
        when(mockSecurity.getUsername()).thenReturn("mockEmail");
        when(service.findByEmail("mockEmail")).thenReturn(Optional.of(user));
        when(user.getId()).thenReturn(1L);

        //Calling the method
        String result = controller.startingPage(model);

        Assertions.assertEquals("hello", result);
        //Assertions.assertEquals(1L, model.getAttribute("userId"));
        //Assertions.assertEquals("mockEmail", model.getAttribute("email"));
    }

    @Test
    void testAddUserPage() {
        //Calling the method
        String result = controller.addUserPage(model);

        Assertions.assertEquals("addUser", result);
    }

    @Test
    void testViewUserPage() {
        TBUser user = mock(TBUser.class);
        when(service.findById(1L)).thenReturn(Optional.of(user));
        when(user.getId()).thenReturn(1L);

        //Calling the method
        String result = controller.viewUserPage(1L, model);

        Assertions.assertEquals("viewUser", result);
    }

    @Test
    void testViewUsersPage() {
        TBUser user = mock(TBUser.class);
        when(service.findAllUsers()).thenReturn(List.of(user));

        //Calling the method
        String result = controller.viewUsersPage(model);

        Assertions.assertEquals("viewAllUsers", result);
    }

    @Test
    void testAddNewUserPage() {
        TBUser user = mock(TBUser.class);
        when(service.save(user)).thenReturn(user);

        //Calling the method
        String result = controller.addNewUserPage(user, model);

        Assertions.assertEquals("viewAllUsers", result);
    }

}
