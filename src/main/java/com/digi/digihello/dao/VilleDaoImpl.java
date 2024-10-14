package com.digi.digihello.dao;

import java.util.List;

import com.digi.digihello.model.Ville;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class VilleDaoImpl implements VilleDao {

	@PersistenceContext
	private EntityManager entityManager;

	public List<Ville> findAll() {
		return entityManager.createQuery("SELECT v FROM Ville v", Ville.class).getResultList();
	}

	
	public Ville findById(int id) {
		return entityManager.find(Ville.class, id);
	}

	
	public Ville findByName(String nom) {
		return entityManager.createQuery("SELECT v FROM Ville v WHERE v.nom = :nom", Ville.class)
				.setParameter("nom", nom).getSingleResult();
	}

	@Override
	public void save(Ville ville) {
		if (ville.getId() == 0) {
			entityManager.persist(ville); // insertion
		} else {
			entityManager.merge(ville); // mise à jour
		}
	}

	
	public void deleteById(int id) {
		Ville ville = findById(id);
		if (ville != null) {
			entityManager.remove(ville);
		}
	}
}
