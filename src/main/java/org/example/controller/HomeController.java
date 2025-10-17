package org.example.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@CrossOrigin(origins = "*")
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Welcome to My Spring Boot + Thymeleaf Website!");
        model.addAttribute("message", "This is a simple one-page website built with Spring Boot and Thymeleaf. Thank You");
        return "index";
    }
}
