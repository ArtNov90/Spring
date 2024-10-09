package com.digi.digihello.restcontroller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

import com.digi.digihello.model.Departement;
import com.digi.digihello.model.Ville;

@RestController
@RequestMapping("/departement")
public class DepartementController {
	private List<Departement> departements = new ArrayList<>();
	private int nextId = 1;

	// 1. Méthode GET pour lister tous les départements
	@GetMapping("/liste")
	 public List<Departement> listerDepartements() {
	        if (departements.isEmpty()) {
	        	departements.add(new Departement(nextId++, "Ile de France"));
	        	departements.add(new Departement(nextId++, "Hérault"));
	        	departements.add(new Departement(nextId++, "Bouche du Rhone"));
	        	departements.add(new Departement(nextId++, "Haute Garonne"));
	        	departements.add(new Departement(nextId++, "Alpes Maritimes"));
	        }
	        return departements;
	    }

	// 2. Méthode GET pour retourner un département par son id
	@GetMapping("/{id}")
	public ResponseEntity<Departement> afficherDepartementParId(@PathVariable int id) {
		for (Departement d : departements) {
			if (d.getId() == id) {
				return new ResponseEntity<>(d, HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	// 3. Méthode POST pour ajouter un nouveau département
	@PostMapping("/ajouter")
	public ResponseEntity<String> ajouterDepartement(@RequestBody Departement departement) {
		for (Departement d : departements) {
			if (d.getNom().equalsIgnoreCase(departement.getNom())) {
				return new ResponseEntity<>("Le département existe déjà", HttpStatus.BAD_REQUEST);
			}
		}

		departement.setId(nextId++);
		departements.add(departement);
		return new ResponseEntity<>("Département ajouté avec succès", HttpStatus.OK);
	}

	// 4. Méthode PUT pour modifier un département
	@PutMapping("/modifier/{id}")
	public ResponseEntity<String> modifierDepartement(@PathVariable int id,
			@RequestBody Departement departementModifie) {
		for (int i = 0; i < departements.size(); i++) {
			Departement d = departements.get(i);
			if (d.getId() == id) {
				d.setNom(departementModifie.getNom());
				return new ResponseEntity<>("Département mis à jour avec succès", HttpStatus.OK);
			}
		}
		return new ResponseEntity<>("Département non trouvé", HttpStatus.NOT_FOUND);
	}

	// 5. Méthode DELETE pour supprimer un département
	@DeleteMapping("/supprimer/{id}")
	public ResponseEntity<String> supprimerDepartement(@PathVariable int id) {
		for (int i = 0; i < departements.size(); i++) {
			Departement d = departements.get(i);
			if (d.getId() == id) {
				departements.remove(i);
				return new ResponseEntity<>("Département supprimé avec succès", HttpStatus.OK);
			}
		}
		return new ResponseEntity<>("Département non trouvé", HttpStatus.NOT_FOUND);
	}

	// 6. Méthode GET pour lister les n plus grandes villes d’un département
	@GetMapping("/{id}/grandes-villes/{n}")
	public ResponseEntity<List<Ville>> listerNPlusGrandesVilles(@PathVariable int id, @PathVariable int n) {
		for (Departement d : departements) {
			if (d.getId() == id) {
				List<Ville> grandesVilles = d.getVilles().stream()
						.sorted((v1, v2) -> Integer.compare(Integer.parseInt(v2.getNbHabitants().replaceAll(",", "")),
								Integer.parseInt(v1.getNbHabitants().replaceAll(",", ""))))
						.limit(n).collect(Collectors.toList());
				return new ResponseEntity<>(grandesVilles, HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	// 7. Méthode GET pour lister les villes d’un département avec une population
	// entre min et max
	@GetMapping("/{id}/villes-par-population/{min}/{max}")
	public ResponseEntity<List<Ville>> listerVillesParPopulation(@PathVariable int id, @PathVariable int min,
			@PathVariable int max) {
		for (Departement d : departements) {
			if (d.getId() == id) {
				List<Ville> villesParPopulation = d.getVilles().stream().filter(v -> {
					int population = Integer.parseInt(v.getNbHabitants().replaceAll(",", ""));
					return population >= min && population <= max;
				}).collect(Collectors.toList());
				return new ResponseEntity<>(villesParPopulation, HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
