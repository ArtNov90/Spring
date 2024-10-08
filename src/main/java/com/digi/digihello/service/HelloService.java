package com.digi.digihello.service;

import org.springframework.stereotype.Service;

@Service
public class HelloService {

	public String salutations() {
		return "Je suis la classe de Service et je vous dis Bonjour !";

	}
}
