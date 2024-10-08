package com.digi.digihello.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digi.digihello.service.HelloService;

@RestController
@RequestMapping("/hello")
public class HelloControleur {

	@Autowired
	private HelloService helloService;

	@GetMapping
	public String direHello() {
		return helloService.salutations(); // Appel de la méthode salutations() de HelloService
	}
}