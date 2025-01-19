package com.example.selfWeb.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class webController {
    @GetMapping("/")
    public String index( Model model ) {
        model.addAttribute("title", "Verónica Sánchez Justicia");
        model.addAttribute("subtitle", "Informática");
        model.addAttribute("description", "Desarrolladora de Aplicaciones Móviles, Programas de " + 
                        "Escritorio, Paginas Web y Administradora de Sistemas");
                        
        return "index";
    }
    
    @GetMapping("/aboutme")
    public String aboutMe( Model model ) {
        return "aboutMe";
    }

    @GetMapping("/experience")
    public String experience( Model model ) {
        return "experience";
    }

    @GetMapping("/skills")
    public String skills( Model model ) {
        return "skills";
    }

    @GetMapping("/contact")
    public String contact( Model model ) {
        model.addAttribute("telefon", "+34 644048205");
        model.addAttribute("email", "veronicasanchezjusticiainfor@gmail.com");
        model.addAttribute("linkedin", 
                "https://www.linkedin.com/in/ver%C3%B3nica-s%C3%A1nchez-justicia-825a7421a/");
        model.addAttribute("github", "https://github.com/VeronicaSj");
        
        return "contact";
    }
} 