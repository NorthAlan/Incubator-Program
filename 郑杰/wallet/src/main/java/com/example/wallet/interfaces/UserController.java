package com.example.wallet.interfaces;
// UserController.java - REST控制器

import com.example.wallet.application.commands.UserRequest;
import com.example.wallet.application.services.UserService;
import com.example.wallet.domain.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserRequest userRequest) {
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        // 假设这里进行了密码加密处理
        // user.setPassword(encryptedPassword);
        user.setPassword(userRequest.getPassword());
        user.setPhoneNumber(userRequest.getPhoneNumber());

        return ResponseEntity.ok(userService.createUser(user));
//        return userService.createUser(user)
//                .map(u -> ResponseEntity.ok(u))
//                .orElse(ResponseEntity.badRequest().build());
    }




    // Other controller methods...
}