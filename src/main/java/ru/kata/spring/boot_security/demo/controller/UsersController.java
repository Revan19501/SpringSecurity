package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
public class UsersController {
    private final RoleRepository roleRepository;
    private UserService userService;

    @Autowired
    UsersController(RoleRepository roleRepository, UserService userService) {
        this.roleRepository = roleRepository;
        this.userService = userService;
    }

    @GetMapping()
    public String start() {
        return "index";
    }

//    @GetMapping("/allusers")
//    public String showAllUsers(Model model) {
//        List<User> users = userService.getAllUsers();
//        model.addAttribute("users", users);
//        return "show";
//    }

//    @GetMapping("admin/adduser")
//    public String addUser(Model model) {
//        User user = new User();
//        model.addAttribute("user", user);
//        return "admin";
  //  }

    @GetMapping("/adduser")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("allRoles", roleRepository.findAll()); // Передаем роли в форму
        return "add";
    }

    @PostMapping("/add")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.saveUser(user); // Вызываем метод, который добавит роль
        return "redirect:/users/";
    }
//    }
//
//    @GetMapping("/updateuser")
//    public String updateUser(@RequestParam("id") int id, Model model) {
//        User user = userService.getUserById(id);
//        model.addAttribute("user", user);
//        return "adduser";
//    }
//    @GetMapping("/deleteuser")
//    public String deleteUser(@RequestParam("id") int id, Model model) {
//        userService.deleteUserById(id);
//        return "redirect:/users/allusers";
//    }

}
