package com.digi.digihello.dao;

import java.util.List;

import com.digi.digihello.model.Ville;

public interface VilleDao {
    List<Ville> findAll(); // Retourne toutes les villes

    Ville findById(int id); // Trouve une ville par ID

    Ville findByName(String nom); // Trouve une ville par nom

    void save(Ville ville); // Sauvegarde ou met à jour une ville

    void deleteById(int id); // Supprime une ville par ID
}