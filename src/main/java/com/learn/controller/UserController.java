package com.learn.controller;

import com.learn.dto.UserDTO;
import com.learn.service.impl.RoleServiceImpl;
import com.learn.service.impl.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    private final RoleServiceImpl roleService;
    private final UserServiceImpl userService;

    public UserController(RoleServiceImpl roleService, UserServiceImpl userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping("/create")
    public String createUser( Model model){

        model.addAttribute("user", new UserDTO());
        model.addAttribute("roles",roleService.findAll() );
        model.addAttribute("users",userService.findAll() );

        return "user/create";
    }

    @PostMapping("/create")
    public String insertUser(Model model, @ModelAttribute("user") UserDTO user){

        model.addAttribute("user", new UserDTO());
        model.addAttribute("roles",roleService.findAll() );

        userService.save(user);
        model.addAttribute("users",userService.findAll() );



        return "user/create";
    }

}
