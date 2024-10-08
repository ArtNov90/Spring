package com.digi.digihello.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloControleur {

    private final HelloService helloService;

    // Injection par constructeur
    @Autowired
    public HelloControleur(HelloService helloService) {
        this.helloService = helloService;
    }

    // Mise à jour de la méthode direHello pour retourner le message de salutations() 
    @GetMapping
    public String direHello() {
        return helloService.salutations();  // Appel de la méthode salutations() de HelloService
    }
}