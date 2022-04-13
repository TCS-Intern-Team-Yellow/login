package com.yellow.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/login/user")
public class LoginController {

    @Autowired //we only declare, Autowired will help in automatically initiating it
    private UserRepo userRepo;

    @PostMapping("/signup")
    public User signup(@RequestBody User user) throws NoSuchAlgorithmException {
        String encryptedPassword = getDigest(user.getPassword());
        user.setPassword(encryptedPassword);
        return userRepo.save(user);
    }

    @GetMapping("/userid/{userId}")
    public Optional<User> getUserDetails(@PathVariable String userId) {
        return userRepo.findById(userId);
    }

    private String getDigest(String password) throws NoSuchAlgorithmException {
        byte[] passwordBytes = password.getBytes(StandardCharsets.UTF_8);

        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] result = md.digest(passwordBytes);

        StringBuilder sb = new StringBuilder();
        for (byte b : result) {
            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }

}
