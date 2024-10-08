package com.digi.digihello.rest;


import java.util.ArrayList;
import java.util.List;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.digi.digihello.villes.Ville;


	
@RestController
@RequestMapping("/ville")
public class VilleController {

    @GetMapping("/liste")
    public List<Ville> afficherVilles() {
        List<Ville> villes = new ArrayList<>();

        
        villes.add(new Ville("Paris", "2,161,000"));
        villes.add(new Ville("Lyon", "515,695"));
        villes.add(new Ville("Marseille", "861,635"));
        villes.add(new Ville("Toulouse", "493,465"));
        villes.add(new Ville("Nice", "342,295"));

        return villes;
    }
}
	


