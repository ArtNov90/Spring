package com.digi.digihello.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.digi.digihello.model.Departement;
@Repository
public interface DepartementRepository extends JpaRepository<Departement, Integer> {
	public Departement findByCode(String code);
	public void deleteByCode(String code);
}
