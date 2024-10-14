package com.digi.digihello.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.digi.digihello.model.Ville;

@Repository
public interface VilleRepository extends JpaRepository<Ville, Integer> {
    // 1. Recherche de toutes les villes dont le nom commence par une chaîne donnée
    List<Ville> findByNomStartingWith(String prefix);

    // 2. Recherche de toutes les villes dont la population est supérieure à un certain minimum
    List<Ville> findByNbHabitantsGreaterThan(int min); // Utiliser int

    // 3. Recherche de toutes les villes dont la population est entre un minimum et un maximum
    List<Ville> findByNbHabitantsGreaterThanAndNbHabitantsLessThan(int min, int max); // Utiliser int

    // 4. Recherche de toutes les villes d'un département dont la population est supérieure à min
    List<Ville> findByDepartementIdAndNbHabitantsGreaterThan(int departementId, int min); // Utiliser int

    // 5. Recherche de toutes les villes d'un département dont la population est entre min et max
    List<Ville> findByDepartementIdAndNbHabitantsGreaterThanAndNbHabitantsLessThan(int departementId, int min, int max); // Utiliser int

    // 6. Recherche des n villes les plus peuplées d'un département donné (en utilisant Pageable pour limiter le nombre de résultats)
    Page<Ville> findByDepartementIdOrderByNbHabitantsDesc(int departementId, Pageable pageable);
}



