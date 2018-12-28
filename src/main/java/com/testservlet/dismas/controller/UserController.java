package com.testservlet.dismas.controller;

import com.testservlet.dismas.domain.Role;
import com.testservlet.dismas.domain.User;
import com.testservlet.dismas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by Dmitry on 15.12.2018.
 */

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    //private UserRepo userRepo;
    private UserService userService;

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping
    public String userList (Model model){
        model.addAttribute("users",userService.findAll());
        return "userList";
    }
    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model){
        model.addAttribute("user",user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }
    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String ,String> form,
            @RequestParam("userId") User user
    ){
        userService.saveUser(user,username,form);

        return "redirect:user";
    }

    @GetMapping("profile")
    public  String getProfile(Model model, @AuthenticationPrincipal User user){
                model.addAttribute("username",user.getUsername());
                model.addAttribute("email",user.getEmail());

                return "profile";
    }

    @PostMapping("profile")
    public String updateProfile(
            @AuthenticationPrincipal User user,
            @RequestParam String password,
            @RequestParam String email
    ) {
        userService.updateProfile(user, password, email);

        return "redirect:/user/profile";
    }
}
