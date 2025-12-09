package com.senac.ministock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@CrossOrigin("*")
public class PresentationController {

    @GetMapping("/")
    public String home() {
        return "forward:/apresentacao.html";
    }
    
}
