package com.digi.digihello.service;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;
import com.digi.digihello.model.Ville;
import com.digi.digihello.repository.VilleRepository;


@ActiveProfiles("test")
public class VilleServiceTest {
	@Autowired
	private VilleService villeService;

	@Autowired
	private VilleRepository villeRepository;

	@Test
	public void testExtractVilles() {
		// Exécuter la méthode pour extraire toutes les villes
		List<Ville> villesExtraites = villeService.extractVilles();

		// Récupérer toutes les villes présentes dans la base de données
		List<Ville> villesAttendues = villeRepository.findAll();

		// Vérifier que le nombre de villes extraites correspond bien aux villes
		// attendues
		assertThat(villesExtraites.size()).isEqualTo(villesAttendues.size());

		// Comparer les noms des villes pour s'assurer que toutes les villes sont bien
		// présentes
		List<String> nomsVillesExtraites = villesExtraites.stream().map(Ville::getNom).collect(Collectors.toList());

		List<String> nomsVillesAttendues = villesAttendues.stream().map(Ville::getNom).collect(Collectors.toList());

		// Vérifier que les deux listes de noms de villes sont identiques
		assertThat(nomsVillesExtraites).containsExactlyInAnyOrderElementsOf(nomsVillesAttendues);
	}
}
