package com.example.timesheet.controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String helloPage(@RequestParam String username) {
        return "<h1>Hello, " + username + "</h1>";
    }

    @GetMapping("/hello/{username}")
    public String helloPagePathVariable(@PathVariable String username) {
        return "<h1>Hello, " + username + "</h1>";
    }
}
