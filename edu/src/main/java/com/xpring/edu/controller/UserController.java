package com.xpring.edu.controller;

import com.xpring.edu.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("allUsers", userService.findAll());
        return "users";
    }

    @PostMapping("/enable/{username}")
    public String enableUser(@PathVariable String username) {
        userService.enableUser(username);
        return "redirect:/users";
    }

    @PostMapping("/disable/{username}")
    public String disableUser(@PathVariable String username) {
        userService.disableUser(username);
        return "redirect:/users";
    }

    @PostMapping("delete/{username}")
    public String deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
        return "redirect:/users";
    }
}
