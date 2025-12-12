package com.dhrmarques.ermdemo.controller;

import com.dhrmarques.ermdemo.model.SecurityTBUser;
import com.dhrmarques.ermdemo.model.TBUser;
import com.dhrmarques.ermdemo.service.TBUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/erm")
public class ErmController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ErmController.class);

    private final TBUserService service;

    @Autowired
    public ErmController(TBUserService service) {this.service = service;}

    @GetMapping
    public String startingPage(Model model) {
        SecurityTBUser principal = getSecurityTBUser();
        String email = principal != null ? principal.getUsername() : null;

        Optional<TBUser> user = service.findByEmail(email);
        user.ifPresent(tbUser -> model.addAttribute("userId", tbUser.getId()));

        model.addAttribute("email", email);
        return "hello";
    }

    @GetMapping("/createUser")
    public String addUserPage(Model model) {
        model.addAttribute("newUser", new TBUser());
        return "addUser";
    }

    @GetMapping("/user/{id}")
    public String viewUserPage(@PathVariable Long id, Model model) {
        Optional<TBUser> user = service.findById(id);
        user.ifPresent(tbUser -> model.addAttribute("user", tbUser));
        return "viewUser";
    }

    @GetMapping("/users")
    public String viewUsersPage(Model model) {
        List<TBUser> users = service.findAllUsers();
        model.addAttribute("users", users);
        return "viewAllUsers";
    }

    @PostMapping("/createUser")
    public String addNewUserPage(@ModelAttribute TBUser newUser, Model model) {
        try {
            service.save(newUser);
        } catch (Exception e) {
            LOGGER.error("An error has occurred attempting to create the user {}", newUser, e);
        }

        //Reload users list before opening page
        return viewUsersPage(model);
    }

    /**
     * Retrieves the logged user email (AKA its username).
     * @return The user email, as a Principal
     */
    private static SecurityTBUser getSecurityTBUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // Principal here is the security user, not the user (comes from auth process)
        SecurityTBUser principal = (SecurityTBUser) auth.getPrincipal();
        return principal;
    }

}
