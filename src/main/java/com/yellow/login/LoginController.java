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
        user.setPassword(this.digest(user.getPassword().getBytes(StandardCharsets.UTF_8)));
        return userRepo.save(user);
    }
    
    @PostMapping("/login")
    public User login(@RequestBody User user) throws NoSuchAlgorithmException {
    	user.setPassword(this.digest(user.getPassword().getBytes(StandardCharsets.UTF_8)));
    	User u=userRepo.findByEmailId(user.getEmailId());
    	if(u!=null && u.getPassword().equals(user.getPassword())) {
    		return u;
    	}else {
    		return user;
    	}
    }

    @GetMapping("/userid/{userId}")
    public Optional<User> getUserDetails(@PathVariable String userId) {
        return userRepo.findById(userId);
    }

    @GetMapping("/remove/{userId}")
    public boolean removeUserDetail(@PathVariable String userId) {
    	Optional<User> user=this.getUserDetails(userId);
    	if(user.isPresent()){
        	userRepo.delete(user.get());
        	return true;
    	}else {    		
    		return false;
    	}
    }
    
    private String digest(byte[] password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] result = md.digest(password);
        StringBuilder sb = new StringBuilder();
        for (byte b : result) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
