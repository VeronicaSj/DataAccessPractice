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

} 