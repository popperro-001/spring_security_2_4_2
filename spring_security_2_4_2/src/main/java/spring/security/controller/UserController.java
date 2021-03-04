package spring.security.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.security.entity.User;
import spring.security.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping()
    public String userService(Model model, Authentication authentication){
        User user = (User) authentication.getPrincipal();
        model.addAttribute("user", user);

        return "user";
    }


}
