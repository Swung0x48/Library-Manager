package com.swung0x48.librarymanager.controller;

import com.swung0x48.librarymanager.domain.LibUser;
import com.swung0x48.librarymanager.exception.AuthException;
import com.swung0x48.librarymanager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping(value = "/role")
    public Map<String, Object> getUserRole(HttpServletRequest request) {
        String role = (String) request.getAttribute("role");

        Map<String, Object> map = new HashMap<>();
        map.put("role", role);
        return map;
    }

    @GetMapping(value = "")
    @ResponseStatus(HttpStatus.OK)
    public List<LibUser> allUser() {
        return userService.getAllUsers();
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Integer deleteUser(@PathVariable Integer id) {
        return userService.deleteUser(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public LibUser addUser(@RequestBody LibUser libUser) {
        return userService.addUser(libUser);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, String> loginUser(@RequestBody Map<String, Object> userRequest) {
        String username = (String) userRequest.get("username");
        String password = (String) userRequest.get("password");

        // TODO: validate user
        LibUser user = userService.validateUser(username, password);

        Map<String, String> map = new HashMap<>();
        if (user == null) {
            throw new AuthException("Login failed. Maybe a wrong username/password?");
        }

        map.put("token", userService.GenerateJwtToken(user));
        return map;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, String> registerUser(@RequestBody Map<String, Object> userRequest) {
        String password = (String) userRequest.get("password");
        String username = (String) userRequest.get("username");

        // TODO: Save registered user into database
        LibUser user = userService.registerUser(username, password, "vip0"); // TODO: role hardcoded.

        Map<String, String> map = new HashMap<>();
        map.put("token", userService.GenerateJwtToken(user));
        return map;
    }

    @PutMapping(value = "/ban/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public LibUser banUser(@PathVariable Integer id) {
        return userService.banUser(id);
    }

    @PutMapping(value = "/unban/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public LibUser unbanUser(@PathVariable Integer id) {
        return userService.unbanUser(id);
    }
}
