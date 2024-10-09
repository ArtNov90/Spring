package com.digi.digihello.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digi.digihello.dao.VilleDao;
import com.digi.digihello.model.Ville;

@Service
public class VilleService {
	 @Autowired
	    private VilleDao villeDao;

	    // Méthode pour extraire toutes les villes
	    public List<Ville> extractVilles() {
	        return villeDao.findAll();
	    }

	    // Méthode pour extraire une ville par son ID
	    public Ville extractVille(int idVille) {
	        return villeDao.findById(idVille);
	    }

	    // Méthode pour extraire une ville par son nom
	    public Ville extractVille(String nom) {
	        return villeDao.findByName(nom);
	    }

	    // Méthode pour insérer une nouvelle ville et retourner la liste mise à jour
	    public List<Ville> insertVille(Ville ville) {
	        villeDao.save(ville);
	        return villeDao.findAll();
	    }

	    // Méthode pour modifier une ville et retourner la liste mise à jour
	    public List<Ville> modifierVille(int idVille, Ville villeModifiee) {
	        Ville villeExistante = villeDao.findById(idVille);
	        if (villeExistante != null) {
	            villeExistante.setNom(villeModifiee.getNom());
	            villeExistante.setNbHabitants(villeModifiee.getNbHabitants());
	            villeDao.save(villeExistante);
	        }
	        return villeDao.findAll();
	    }

	    // Méthode pour supprimer une ville par son ID et retourner la liste mise à jour
	    public List<Ville> supprimerVille(int idVille) {
	        villeDao.deleteById(idVille);
	        return villeDao.findAll();
	    }
}
