package com.reto.pedidos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class pedido {
    
    @GetMapping("/saludo")
    public String saludar() {
        return "Hola, bienvenido a Spring Boot!";
    }
}
