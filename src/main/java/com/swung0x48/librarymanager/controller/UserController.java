package com.swung0x48.librarymanager.controller;

import com.swung0x48.librarymanager.domain.LibUser;
import com.swung0x48.librarymanager.exception.AuthException;
import com.swung0x48.librarymanager.repository.UserRepository;
import com.swung0x48.librarymanager.services.IUserService;
import com.swung0x48.librarymanager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody Map<String, Object> userRequest) {
        String username = (String) userRequest.get("username");
        String password = (String) userRequest.get("password");

        // TODO: validate user
        LibUser user = userService.validateUser(username, password);

        Map<String, String> map = new HashMap<>();
        if (user == null) {
            throw new AuthException("Login failed. Maybe a wrong username/password?");
        }

        map.put("token", userService.GenerateJwtToken(user));
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody Map<String, Object> userRequest) {
        String password = (String) userRequest.get("password");
        String username = (String) userRequest.get("username");

        // TODO: Save registered user into database
        LibUser user = userService.registerUser(username, password, "ADMIN"); // TODO: role hardcoded.

        Map<String, String> map = new HashMap<>();
        map.put("token", userService.GenerateJwtToken(user));
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }
}
