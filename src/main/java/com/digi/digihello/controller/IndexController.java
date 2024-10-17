package com.digi.digihello.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	@GetMapping
	public String getIndex(Model model, Authentication authentication) {
		model.addAttribute("authentication", authentication); // Changer le nom ici
		return "index"; // Assurez-vous que ce nom de vue est correct
	}

}
