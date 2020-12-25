package com.swung0x48.librarymanager.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @GetMapping("")
    public String getAllBooks(HttpServletRequest request) {
        int userId = (Integer) request.getAttribute("userId");

        return "userId: " + userId;
    }
}
