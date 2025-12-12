package com.dhrmarques.ermdemo.controller;

import com.dhrmarques.ermdemo.model.TBUser;
import com.dhrmarques.ermdemo.model.TBUserProject;
import com.dhrmarques.ermdemo.service.TBUserProjectService;
import com.dhrmarques.ermdemo.service.TBUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/projects")
public class ErmProjectController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ErmProjectController.class);

    private final TBUserProjectService service;
    private final TBUserService userService;

    @Autowired
    public ErmProjectController(TBUserProjectService service, TBUserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @GetMapping
    public String viewProjectsPage(Model model) {
        List<TBUserProject> projects = service.findAllProjects();
        model.addAttribute("projects", projects);
        return "viewAllProjects";
    }

    @GetMapping("/{userId}")
    public String viewUserProjectsPage(@PathVariable Long userId, Model model) {
        List<TBUserProject> projects = service.findByUserId(userId);
        model.addAttribute("userProjects", projects);
        return "viewUserProjects";
    }

    @GetMapping("/createProject/{userId}")
    public String saveProjectLandingPage(@PathVariable Long userId, Model model) {
        //Can be refactored to take into account current view user
        TBUserProject userProject = new TBUserProject();
        Optional<TBUser> user = userService.findById(userId);

        if (user.isPresent()) {
            userProject.setUser(user.get());
            model.addAttribute("userId", userId);
            model.addAttribute("newProject", userProject);
        }
        return "addProject";
    }

    @PostMapping("/createProject/{userId}")
    public String saveProjectPage(@ModelAttribute TBUserProject newProject, @PathVariable Long userId, Model model) {
        try {
            service.save(newProject);
        } catch (Exception e) {
            LOGGER.error("An error has occurred attempting to create the project {}", newProject, e);
        }
        return saveProjectLandingPage(userId, model);
    }
}
