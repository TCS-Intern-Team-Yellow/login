package com.yellow.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/login/user")
public class LoginController {

    @Autowired
    private UserRepo userRepo;

    @PostMapping("/signup")
    public User signup(@RequestBody User user) {
        return userRepo.save(user);
    }

    @GetMapping("/userid/{userId}")
    public Optional<User> getUserDetails(@PathVariable String userId) {
        return userRepo.findById(userId);
    }

}
