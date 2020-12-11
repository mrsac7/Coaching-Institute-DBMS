package com.xpring.edu.controller;

import java.security.Principal;

import com.xpring.edu.model.Notification;
import com.xpring.edu.model.User;
import com.xpring.edu.services.NotificationService;
import com.xpring.edu.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NotificationController {

    @Autowired
    private NotificationService notificationService;
    @Autowired
    private UserService userService;
    
    @GetMapping("/notification/add")
    public String add() {
        return "add_notification";
    }
    
    @PostMapping("notification/add")
    public String process_add(Notification notification, Principal principal) {
        User user = userService.getUser(principal.getName());
        notification.setEmailID(user.getEmail());
        notificationService.add(notification);
        return "redirect:/home";
    }
    
    @GetMapping("/notification/{notificationID}")
    public String view(@PathVariable int notificationID, Model model) {
        Notification notification = notificationService.get(notificationID);
        model.addAttribute("notification", notification);
        return "notification";
    }
    
    @GetMapping("/notification/{notificationID}/edit")
    public String edit(@PathVariable int notificationID, Model model) {
        Notification notification = notificationService.get(notificationID);
        model.addAttribute("notification", notification);
        return "edit_notification";
    }
    @PostMapping("/notification/{notificationID}/edit")
    public String processEdit(Notification notification) {
        notificationService.update(notification);
        return "redirect:/home";
    }

    @PostMapping("notification/{notificationID}/delete")
    public String delete(@PathVariable int notificationID) {
        notificationService.delete(notificationID);
        return "redirect:/home";
    }
}
