package com.digi.digihello.restcontroller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.client.RestTemplate;

import com.digi.digihello.model.Departement;
import com.digi.digihello.model.Ville;
import com.digi.digihello.repository.DepartementRepository;
import com.digi.digihello.service.VilleService;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/departement")
public class DepartementController {
	// private List<Departement> departements = new ArrayList<>();
	@Autowired
	private DepartementRepository departementRepository;
	
	@Autowired
	    private VilleService villeService;

	    @Autowired
	    private RestTemplate restTemplate; // Utilisé pour faire des appels REST
	    public DepartementController(RestTemplate restTemplate) {
	        this.restTemplate = restTemplate;
	    }

	    @GetMapping("/export/pdf")
	    public ResponseEntity<Void> exportDepartementsToPDF(HttpServletResponse response) {
	        // Récupérer tous les départements depuis la base de données
	        List<Departement> departements = departementRepository.findAll();

	        if (departements.isEmpty()) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }

	        // Préparer la réponse PDF
	        response.setContentType("application/pdf");
	        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=departements.pdf");

	        Document document = new Document();
	        try {
	            PdfWriter.getInstance(document, response.getOutputStream());
	            document.open();

	            // Ajouter un titre
	            document.add(new Paragraph("Liste des Départements"));

	            for (Departement departement : departements) {
	                document.add(new Paragraph("Nom: " + departement.getNom() + ", Code: " + departement.getCode()));
	            }

	            document.close();
	        } catch (DocumentException | IOException e) {
	            e.printStackTrace(); // Afficher l'erreur dans la console pour le débogage
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }

	        return new ResponseEntity<>(HttpStatus.OK);
	    }

	        

	// 1. Méthode GET pour lister tous les départements
	// @GetMapping("/liste")
	@GetMapping("/liste")
	public List<Departement> listerDepartements() {
		return departementRepository.findAll();
	}

	// 2. Méthode GET pour retourner un département par son id
	@GetMapping("/departement/{id}")
	public ResponseEntity<Departement> afficherDepartementParId(@PathVariable int id) {
		Departement departement = departementRepository.findById(id).orElse(null);
		if (departement != null) {
			return new ResponseEntity<>(departement, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	// 3. Méthode POST pour ajouter un nouveau département
	@PostMapping("/ajouter")
	public ResponseEntity<String> ajouterDepartement(@RequestBody Departement departement) {
		if (departement.getNom() == null || departement.getNom().length() < 3) {
			return new ResponseEntity<>("Le nom du département est obligatoire et doit contenir au moins 3 lettres.",
					HttpStatus.BAD_REQUEST);
		}
		departementRepository.save(departement);
		return new ResponseEntity<>("Département ajouté avec succès", HttpStatus.OK);
	}

	@PutMapping("/modifier/{id}")
	public ResponseEntity<String> modifierDepartement(@PathVariable int id,
			@RequestBody Departement departementModifie) {

		// Cherche le département par son id dans la base de données
		Departement departement = departementRepository.findById(id).orElse(null);

		// Si le département n'existe pas, renvoie une réponse 404
		if (departement == null) {
			return new ResponseEntity<>("Département non trouvé", HttpStatus.NOT_FOUND);
		}

		// Si le département existe, mets à jour ses informations
		departement.setNom(departementModifie.getNom());
		departement.setCode(departementModifie.getCode());

		// Sauvegarde le département mis à jour dans la base de données
		departementRepository.save(departement);

		// Retourne une réponse 200 OK avec un message de succès
		return new ResponseEntity<>("Département mis à jour avec succès", HttpStatus.OK);
	}

	@DeleteMapping("/supprimer/{id}")
	public ResponseEntity<String> supprimerDepartement(@PathVariable int id) {
		// Cherche le département par son id dans la base de données
		if (!departementRepository.existsById(id)) {
			return new ResponseEntity<>("Département non trouvé", HttpStatus.NOT_FOUND);
		}

		// Si le département existe, le supprime
		departementRepository.deleteById(id);

		// Retourne une réponse 200 OK avec un message de succès
		return new ResponseEntity<>("Département supprimé avec succès", HttpStatus.OK);
	}

	// 7. Méthode GET pour lister les villes d’un département avec une population
	// entre min et max
	// @GetMapping("/{id}/villes-par-population/{min}/{max}")
	// public ResponseEntity<List<Ville>> listerVillesParPopulation(@PathVariable
	// int id, @PathVariable int min, @PathVariable int max) {
	// Cherche le département par son id dans la base de données

}
