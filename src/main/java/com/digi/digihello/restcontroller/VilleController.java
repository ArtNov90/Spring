package com.digi.digihello.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.digi.digihello.model.Ville;
import com.digi.digihello.repository.VilleRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ville")
public class VilleController {

    @Autowired
    private VilleRepository villeRepository;

    // 1. Méthode GET pour retourner la liste paginée des villes
    @GetMapping("/liste/paginee")
    public List<Ville> afficherVillesPaginees(@RequestParam int page, @RequestParam int size) {
        PageRequest pageable = PageRequest.of(page, size);  // PageRequest pour pagination
        return villeRepository.findAll();  // Appel au repository avec pagination
    }

    // 2. Méthode GET pour retourner une ville par son id
    @GetMapping("/{id}")
    public ResponseEntity<Ville> afficherVilleParId(@PathVariable int id) {
        Optional<Ville> villeOpt = villeRepository.findById(id);  // Utilisation de findById pour rechercher une ville
        return villeOpt.map(ville -> new ResponseEntity<>(ville, HttpStatus.OK))
                       .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // 3. Recherche de toutes les villes dont le nom commence par une chaîne donnée
    @GetMapping("/nom/{prefix}")
    public ResponseEntity<List<Ville>> rechercherVillesParNom(@PathVariable String prefix) {
        List<Ville> villes = villeRepository.findByNomStartingWith(prefix);
        return villes.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(villes, HttpStatus.OK);
    }

    // 4. Recherche de toutes les villes dont la population est supérieure à un certain minimum
    @GetMapping("/population/superieure/{min}")
    public ResponseEntity<List<Ville>> rechercherVillesPopulationSuperieure(@PathVariable int min) {  
        List<Ville> villes = villeRepository.findByNbHabitantsGreaterThan(min);
        return villes.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(villes, HttpStatus.OK);
    }

    @GetMapping("/population/entre/{min}/{max}")
    public ResponseEntity<List<Ville>> rechercherVillesPopulationEntre(@PathVariable int min, @PathVariable int max) {  
        List<Ville> villes = villeRepository.findByDepartementIdAndNbHabitantsGreaterThanAndNbHabitantsLessThan( min, max, max);
        return villes.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(villes, HttpStatus.OK);
    }


    // 6. Recherche de toutes les villes d'un département dont la population est supérieure à min
    @GetMapping("/departement/{departementId}/population/superieure/{min}")
    public ResponseEntity<List<Ville>> rechercherVillesDansDepartementPopulationSuperieure(@PathVariable int min) {
        List<Ville> villes = villeRepository.findByNbHabitantsGreaterThan(min);
        return villes.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(villes, HttpStatus.OK);
    }

    // 7. Recherche de toutes les villes d'un département dont la population est entre min et max
    @GetMapping("/departement/{departementId}/population/entre/{min}/{max}")
    public ResponseEntity<List<Ville>> rechercherVillesDansDepartementPopulationEntre(@PathVariable int departementId, @PathVariable int min, @PathVariable int max) {
        List<Ville> villes = villeRepository.findByDepartementIdAndNbHabitantsGreaterThanAndNbHabitantsLessThan(departementId, min, max);  
        return villes.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(villes, HttpStatus.OK);
    }

    // 8. Recherche des n villes les plus peuplées d'un département donné (utilise la pagination pour n)
   // @GetMapping("/departement/{departementId}/topn/{n}")
   // public ResponseEntity<List<Ville>> rechercherTopNPlusPeupleesDansDepartement(@PathVariable int departementId, @PathVariable int n) {
   //     PageRequest pageable = PageRequest.of(0, n);  
    //    List<Ville> villes = villeRepository.findByDepartementIdOrderByNbHabitantsDesc(departementId, pageable);
       // return villes.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(villes.getContent(), HttpStatus.OK);
    //}

    // 9. Méthode POST pour ajouter une nouvelle ville
    @PostMapping("/ajouter")
    public ResponseEntity<String> ajouterVille(@RequestBody Ville ville) {
        if (villeRepository.existsById(ville.getId())) {
            return new ResponseEntity<>("La ville existe déjà", HttpStatus.BAD_REQUEST);
        }

        villeRepository.save(ville);  
        return new ResponseEntity<>("Ville insérée avec succès", HttpStatus.CREATED);
    }

    // 10. Méthode PUT pour modifier une ville existante
    @PutMapping("/modifier/{id}")
    public ResponseEntity<String> modifierVille(@PathVariable int id, @RequestBody Ville nouvelleVille) {
        if (!villeRepository.existsById(id)) {
            return new ResponseEntity<>("Ville non trouvée", HttpStatus.NOT_FOUND);
        }

        nouvelleVille.setId(id);
        villeRepository.save(nouvelleVille);
        return new ResponseEntity<>("Ville mise à jour avec succès", HttpStatus.OK);
    }

    // 11. Méthode DELETE pour supprimer une ville par son id
    @DeleteMapping("/supprimer/{id}")
    public ResponseEntity<String> supprimerVille(@PathVariable int id) {
        if (!villeRepository.existsById(id)) {
            return new ResponseEntity<>("Ville non trouvée", HttpStatus.NOT_FOUND);
        }

        villeRepository.deleteById(id);
        return new ResponseEntity<>("Ville supprimée avec succès", HttpStatus.OK);
    }
}
