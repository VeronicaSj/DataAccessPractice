package com.bsicgrp.ex1SpringBoot.API_REST;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {
    @GetMapping("users")
    public Map<String,Object> pruebas(  ) {      
        // JSON
        Map<String,Object> body = new HashMap<>();
        body.put("title", "titulo hola mundo");
        body.put("nombre", "nombre");
        body.put("apellidos", "apellidos");

        return body;
        
        // CASERO  
        // return "{ user : "pepe" }";
    }

    @PostMapping("create")
    public String postMethodName(@RequestBody String entity) {
        try {
            File.createTempFile("asda", "adsasd");
        } catch ( Exception ex ) {}        
        return entity.replace("Gomez", "Gómez");
    } 
}
