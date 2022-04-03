package ru.gpb.school.moneytransfer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class SwaggerController {

    @GetMapping
    public String handleSwagger() {
        return "redirect:/swagger-ui.html";
    }
}
