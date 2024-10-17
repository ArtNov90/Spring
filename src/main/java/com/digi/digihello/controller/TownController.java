package com.digi.digihello.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.digi.digihello.service.VilleService;


@Controller
public class TownController {

    @Autowired
    private VilleService villeService;

    // Méthode pour afficher la liste des villes
    @GetMapping("/list-towns")
    public String listTowns(Model model) {
        // Ajouter la liste des villes au modèle pour la vue
        model.addAttribute("villes", villeService.extractVilles());
        return "town"; // Retourne la vue 'town.html'
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/deleteTown/{id}")
	public String deleteTown(@PathVariable int id) {
		villeService.supprimerVille(id);
		return "redirect:/list-towns";
	}
   
}
