package com.digi.digihello.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digi.digihello.exception.ValidationException;
import com.digi.digihello.model.Departement;
import com.digi.digihello.repository.DepartementRepository;

@Service
public class DepartementService {
	@Autowired
	DepartementRepository departmentRepository;
//	@PostConstruct
//	public void init() {	
//		create(new Departement());
//		create(new Departement());
//		create(new Departement());
//	}
	   // Méthode pour créer un département avec validation
    public boolean create(Departement department) throws ValidationException {
        // Vérification que le code département fait entre 2 et 3 caractères
        if (department.getCode().length() < 2 || department.getCode().length() > 3) {
            throw new ValidationException("Le code département doit faire entre 2 et 3 caractères.");
        }
        // Vérification que le nom du département a au moins 3 lettres
        if (department.getNom().length() < 3) {
            throw new ValidationException("Le nom du département doit contenir au moins 3 lettres.");
        }
        
        // Vérification si le code est déjà pris dans la base de données
        Departement existingDepartment = departmentRepository.findByCode(department.getCode());
        if (existingDepartment != null) {
            throw new ValidationException("Le code du département est déjà pris.");
        }

        // Sauvegarde du département
        departmentRepository.save(department);
        return true;
    }
	

	public List<Departement> findAll() {
		return departmentRepository.findAll();
	}

	public Departement findByCode(String code) {
		return departmentRepository.findByCode(code);
	}

	public Optional<Departement> findById(int id) {
	    return departmentRepository.findById(id);
	}

	public boolean update(Departement department) {
	    return departmentRepository.findById(department.getId()).map(existingDepartment -> {
	        existingDepartment.setNom(department.getNom());
	        existingDepartment.setCode(department.getCode());
	        departmentRepository.save(existingDepartment);
	        return true;
	    }).orElse(false);
	}
	

	public boolean delete(int id) {
	    return departmentRepository.findById(id).map(department -> {
	        departmentRepository.deleteById(id);
	        return true;
	    }).orElse(false);
	}
	
}
