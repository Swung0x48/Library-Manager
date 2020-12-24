package com.swung0x48.librarymanager.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody Map<String, Object> user) {
        String username = (String) user.get("username");
        String password = (String) user.get("password");

        // TODO: vaildate user

        Map<String, String> map = new HashMap<>();
        map.put("mes", "logged in");
        map.put(username, password);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody Map<String, Object> user) {
        String password = (String) user.get("password");
        String username = (String) user.get("username");

        // TODO: Save registered user into database

        Map<String, String> map = new HashMap<>();
        map.put("message", "registered successfully");
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }
}
