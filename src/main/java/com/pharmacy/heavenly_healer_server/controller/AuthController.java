package com.pharmacy.heavenly_healer_server.controller;

import com.pharmacy.heavenly_healer_server.model.User;
import com.pharmacy.heavenly_healer_server.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/new-user")
    public String addUser(@RequestBody User user)
    {
        userService.addUser(user);
        return "User is saved";
    }
}
