package com.learn.controller;

import com.learn.dto.ProjectDTO;
import com.learn.dto.UserDTO;
import com.learn.enums.Status;
import com.learn.service.ProjectService;
import com.learn.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/project")
public class ProjectController {

    private final UserService userService;
    private final ProjectService projectService;

    public ProjectController(UserService userService, ProjectService projectService) {
        this.userService = userService;
        this.projectService = projectService;
    }

    @GetMapping("/create")
    public String createProject( Model model){

        model.addAttribute("project", new ProjectDTO());
        model.addAttribute("managers", userService.findManagers());
        model.addAttribute("projects", projectService.findAll());


        return "/project/create";
    }

    @PostMapping("/create")
    public String insertProject(@ModelAttribute("project") ProjectDTO project){
        project.setProjectStatus(Status.OPEN);

        projectService.save(project);

        return "redirect:/project/create";
    }

    @GetMapping("/delete/{projectCode}")
    public String deleteProject( @PathVariable("projectCode") String projectCode){

        projectService.deleteById(projectCode);



        return "redirect:/project/create";
    }

}
