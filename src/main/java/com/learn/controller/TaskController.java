package com.learn.controller;

import com.learn.dto.TaskDTO;
import com.learn.dto.UserDTO;
import com.learn.service.ProjectService;
import com.learn.service.TaskService;
import com.learn.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/task")
public class TaskController {

    private final ProjectService projectService;
    private final UserService userService;
    private final TaskService taskService;


    public TaskController(ProjectService projectService, UserService userService, TaskService taskService) {
        this.projectService = projectService;
        this.userService = userService;
        this.taskService = taskService;
    }

    @GetMapping("/create")
    public String taskCreate(Model model){
        model.addAttribute("task", new TaskDTO());
        model.addAttribute("projects", projectService.findAll());
        model.addAttribute("employees", userService.findEmployees() );

        model.addAttribute("tasks", taskService.findAll());


        return "/task/create";
    }

    @PostMapping("/create")
    public String insertTask(@ModelAttribute("task") TaskDTO task){
        taskService.save(task);
        return "redirect:/task/create";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id){

        taskService.deleteById(id);
        return "redirect:/task/create";
    }

    @GetMapping("/update/{taskId}")
    public String editTask(Model model, @PathVariable ("taskId") Long taskId){

        model.addAttribute("task", taskService.findById(taskId) );
        model.addAttribute("projects", projectService.findAll());
        model.addAttribute("employees", userService.findEmployees() );

        model.addAttribute("tasks", taskService.findAll());



        return "/task/update";
    }

//    @PostMapping("/update/{taskId}")
//    public String updateTask(@ModelAttribute("task") TaskDTO task, @PathVariable ("taskId") Long taskId){
//        task.setId(taskId);
//
//        taskService.update(task);
//
//
//        return "redirect:/task/create";
//    }

    @PostMapping("/update/{id}")
    public String updateTask(@ModelAttribute("task") TaskDTO task){


        taskService.update(task);


        return "redirect:/task/create";
    }
}
