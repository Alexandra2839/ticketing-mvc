package com.learn.controller;

import com.learn.dto.UserDTO;
import com.learn.service.impl.RoleServiceImpl;
import com.learn.service.impl.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

        return "/user/create";
    }

    @PostMapping("/create")
    public String insertUser(@Valid @ModelAttribute("user") UserDTO user, BindingResult bindingResult, Model model){

        if (bindingResult.hasErrors()){
            model.addAttribute("roles",roleService.findAll() );
            model.addAttribute("users",userService.findAll() );
            return "/user/create";
        }

        userService.save(user);

        return "redirect:/user/create";
    }


    @GetMapping("/update/{username}")
    public String editUser(Model model, @PathVariable ("username") String username){

        model.addAttribute("user", userService.findById(username) );
        model.addAttribute("roles",roleService.findAll() );
        model.addAttribute("users",userService.findAll() );



        return "/user/update";
    }

    @PostMapping("/update")
    public String updateUser(@Valid @ModelAttribute("user") UserDTO user){


            userService.update(user);


        return "redirect:/user/create";
    }

    @GetMapping("/delete/{username}")
    public String deleteUser(Model model, @PathVariable ("username") String username){

        userService.deleteById(username);

        return "redirect:/user/create";
    }


}
