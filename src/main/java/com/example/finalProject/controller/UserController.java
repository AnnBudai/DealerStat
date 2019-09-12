package com.example.finalProject.controller;

import com.example.finalProject.domain.Role;
import com.example.finalProject.domain.User;
import com.example.finalProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userService.findAll());
        return "userList";
    }

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("{user}")
    private String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("users", user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }

    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping
    public String userSave(@RequestParam String first_name, @RequestParam String last_name, @RequestParam String username,
                           @RequestParam String email, @RequestParam Map<String, String> form, @RequestParam("id") User user) {
        userService.saveUser(user, first_name, last_name, username, email, form);
        return "redirect:/user";
    }

    @GetMapping("profile")
    public String getProfile(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("username", user.getUsername());
        model.addAttribute("email", user.getEmail());

        return "profile";
    }

    @PostMapping("profile")
    public String updateProfile(@AuthenticationPrincipal User user, @RequestParam String first_name,
                                @RequestParam String last_name, @RequestParam String email, @RequestParam String password) {
        userService.updateProfile(user, first_name, last_name, email, password);

        return "redirect:/user/profile";
    }
}
