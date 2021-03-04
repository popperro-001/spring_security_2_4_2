package spring.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import spring.security.entity.Role;
import spring.security.entity.User;
import spring.security.service.UserService;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping()
    private String userList(Model model){
        model.addAttribute("users", userService.userList());
        return "admin";
    }

    @GetMapping("/addNewUser")
    public String addNewUser(Model model){
        model.addAttribute("user", new User());
        return "user-info";
    }

    @PostMapping("/saveUser")
    public String createUser(@ModelAttribute("user") @Valid User user,
                             BindingResult bindingResult,
                             @RequestParam(required = false) boolean adminRole,
                             @RequestParam(required = false) boolean userRole){
        if (bindingResult.hasErrors()){
            return "user-info";
        }

        Set<Role> roles = new HashSet<>();

        if (adminRole){
            Role role1 = userService.getRoleByName("ROLE_ADMIN");
            roles.add(role1);
        }
        if (userRole){
            Role role2 = userService.getRoleByName("ROLE_USER");
            roles.add(role2);
        }

        user.setRoles(roles);
        userService.addUser(user);
        return "redirect:/admin";

    }

    @DeleteMapping("/{id}/delete")
    public String deleteUser(@PathVariable("id") Long id){
        userService.removeUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String editUser(Model model, @PathVariable("id") Long id){
        model.addAttribute("userById", userService.getUserById(id));
        return "user-edit";
    }
}
