package com.digi.digihello.restcontroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digi.digihello.model.Ville;

@RestController
@RequestMapping("/ville")
public class VilleController {

	private List<Ville> villes = new ArrayList<>();
	private int nextId = 1;

	@GetMapping("/liste")
	public List<Ville> afficherVilles() {

		if (villes.isEmpty()) {
			villes.add(new Ville(nextId++, "Paris", "2,161,000"));
			villes.add(new Ville(nextId++, "Lyon", "515,695"));
			villes.add(new Ville(nextId++, "Marseille", "861,635"));
			villes.add(new Ville(nextId++, "Toulouse", "493,465"));
			villes.add(new Ville(nextId++, "Nice", "342,295"));
		}
		return villes;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Ville> afficherVilleParId(@PathVariable int id) {
		for (Ville v : villes) {
			if (v.getId() == id) {
				return new ResponseEntity<>(v, HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping("/ajouter")
	public ResponseEntity<String> ajouterVille(@RequestBody Ville ville) {

		for (Ville v : villes) {
			if (v.getNom().equalsIgnoreCase(ville.getNom())) {

				return new ResponseEntity<>("La ville existe déjà", HttpStatus.BAD_REQUEST);
			}
		}

		ville.setId(nextId++);
		villes.add(ville);
		return new ResponseEntity<>("Ville insérée avec succès", HttpStatus.OK);
	}

	@PutMapping("/modifier/{id}")
	public ResponseEntity<String> modifierVille(@PathVariable int id, @RequestBody Ville nouvelleVille) {
		for (int i = 0; i < villes.size(); i++) {
			Ville v = villes.get(i);
			if (v.getId() == id) {
				v.setNom(nouvelleVille.getNom());
				v.setNbHabitants(nouvelleVille.getNbHabitants());
				return new ResponseEntity<>("Ville mise à jour avec succès", HttpStatus.OK);
			}
		}

		return new ResponseEntity<>("Ville non trouvée", HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/supprimer/{id}")
	public ResponseEntity<String> supprimerVille(@PathVariable int id) {
		for (int i = 0; i < villes.size(); i++) {
			Ville v = villes.get(i);
			if (v.getId() == id) {
				villes.remove(i);
				return new ResponseEntity<>("Ville supprimée avec succès", HttpStatus.OK);
			}
		}

		return new ResponseEntity<>("Ville non trouvée", HttpStatus.NOT_FOUND);
	}
}
