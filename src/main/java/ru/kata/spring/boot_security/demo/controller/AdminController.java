package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private UserService userService;


    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public String printUsers(Model model) {
        model.addAttribute("users", userService.allUsers());
        return "Admin/admin";
    }
    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "User/new";
    }
    @PostMapping()
    public String create(@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/update")
    public String Edit(Model model, @PathVariable("id") Long id){
        model.addAttribute("user", userService.get(id));
        return "Admin/update";
    }

    @PatchMapping("/{id}")
    public String Update(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        userService.update(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/delete")
    public String Delete(@PathVariable("id") Long id){
        userService.delete(id);
        return "redirect:/admin";
    }
}
