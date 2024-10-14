package com.digi.digihello.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.digi.digihello.model.Ville;

@Repository
public interface VilleRepository extends JpaRepository<Ville, Integer> {

    // Récupère une ville par son nom exact
    public Ville findByNom(String nom);

    // Supprime une ville par son nom
    public void deleteByNom(String nom);
    // Méthode pour extraire une ville par son nom (nom commençant par une chaîne)
    public List<Ville> findByNomStartingWith(String nom);

    // Méthode pour supprimer une ville par son ID
    public void deleteById(Integer id);

    // Méthode pour rechercher des villes dont la population est supérieure à un minimum
    public List<Ville> findByNbHabitantsGreaterThan(int min);

    // Méthode pour rechercher des villes dans un département avec une population entre min et max
    public List<Ville> findByDepartementIdAndNbHabitantsGreaterThanAndNbHabitantsLessThan(
        int departementId, int min, int max);

    // Méthode pour obtenir les n villes les plus peuplées dans un département
    public List<Ville> findByDepartementIdOrderByNbHabitantsDesc(int departementId, Pageable pageable);

    // Méthode pour rechercher des villes par leur code de département
    List<Ville> findByDepartementCode(String codeDepartement);
}



