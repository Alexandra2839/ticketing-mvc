package com.learn.controller;

import com.learn.dto.TaskDTO;
import com.learn.dto.UserDTO;
import com.learn.enums.Status;
import com.learn.service.ProjectService;
import com.learn.service.TaskService;
import com.learn.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public String insertTask(@Valid @ModelAttribute("task") TaskDTO task, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {

            model.addAttribute("projects", projectService.findAll());
            model.addAttribute("employees", userService.findEmployees());
            model.addAttribute("tasks", taskService.findAll());

            return "/task/create";

        }
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
    public String updateTask(@Valid @ModelAttribute("task") TaskDTO task, BindingResult bindingResult, Model model){


        if (bindingResult.hasErrors()) {

            model.addAttribute("projects", projectService.findAll());
            model.addAttribute("employees", userService.findEmployees());
            model.addAttribute("tasks", taskService.findAll());

            return "/task/update";

        }
        taskService.update(task);


        return "redirect:/task/create";
    }

    @GetMapping("/employee/pending-tasks")
    public String employeePendingTasks(Model model){

        model.addAttribute("tasks", taskService.findAllTasksByStatusISNot(Status.COMPLETED));

        return "/task/pending-tasks";
    }

    @GetMapping("/employee/archive")
    public String employeeArchivedTasks(Model model){

        model.addAttribute("tasks", taskService.findAllTasksByStatus(Status.COMPLETED));

        return "/task/archive";
    }

    @GetMapping("/employee/edit/{id}")
    public String employeeEditTask(@PathVariable Long id, Model model) {

        model.addAttribute("task", taskService.findById(id));
//        model.addAttribute("projects", projectService.findAll());
//        model.addAttribute("employees", userService.findEmployees());



        model.addAttribute("statuses", Status.values());
        model.addAttribute("tasks", taskService.findAllTasksByStatusISNot(Status.COMPLETED));



        return "/task/status-update";

    }

    @PostMapping("/employee/update/{id}")
    public String employeeUpdateTask(@Valid @ModelAttribute("task") TaskDTO task, BindingResult bindingResult, Model model){

        if (bindingResult.hasErrors()) {

            model.addAttribute("statuses", Status.values());
            model.addAttribute("tasks", taskService.findAllTasksByStatusISNot(Status.COMPLETED));

            return "/task/status-update";

        }
        taskService.updateStatus(task);

        return "redirect:/task/employee/pending-tasks";
    }
}
