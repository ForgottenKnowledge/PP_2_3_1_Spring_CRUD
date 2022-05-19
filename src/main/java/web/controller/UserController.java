package web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    public UserController() {
    }

    @GetMapping(value = "/")
    public String index(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "index";
    }

    @GetMapping("/{id}")
    public String showUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "show";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        userService.updateUser(id, user);
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/";
    }
}