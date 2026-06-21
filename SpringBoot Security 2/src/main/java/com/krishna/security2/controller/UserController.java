package com.krishna.security2.controller;

import com.krishna.security2.dto.LoginRequest;
import com.krishna.security2.model.User;
import com.krishna.security2.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    public  UserController(UserService userService){
        this.userService=userService;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user){
        return userService.register(user);
    }

    @PostMapping("/login")
    public String login(
            @RequestBody LoginRequest loginRequest) {
        return userService.login(loginRequest);
    }

    @GetMapping("/hello")
    public String hello(){
        return "hey... you can access web";
    }

}
