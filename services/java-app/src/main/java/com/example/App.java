package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;   // ✅ Add this
import org.springframework.web.bind.annotation.RestController; // ✅ Add this

@SpringBootApplication
@RestController   // ✅ add this so Spring knows it's a REST controller
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @GetMapping("/")
    public String home() {
        return "Hello from Java App 🚀 running on Spring Boot!";
    }
}
