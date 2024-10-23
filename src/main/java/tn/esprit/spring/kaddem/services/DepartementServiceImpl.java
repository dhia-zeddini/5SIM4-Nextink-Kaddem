package tn.esprit.spring.kaddem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;

import java.util.List;

@Slf4j
@Service
public class DepartementServiceImpl implements IDepartementService {
	@Autowired
	DepartementRepository departementRepository;

	public List<Departement> retrieveAllDepartements() {
		log.info("Retrieving all departments");
		List<Departement> departements = (List<Departement>) departementRepository.findAll();
		log.debug("Retrieved {} departments", departements.size());
		return departements;
	}

	public Departement addDepartement(Departement d) {
		log.info("Adding department: {}", d);
		Departement savedDepartement = departementRepository.save(d);
		log.info("Added department with ID: {}", savedDepartement.getIdDepart());
		return savedDepartement;
	}

	public Departement updateDepartement(Departement d) {
		log.info("Updating department with ID: {}", d.getIdDepart());
		Departement updatedDepartement = departementRepository.save(d);
		log.info("Updated department: {}", updatedDepartement);
		return updatedDepartement;
	}

	public Departement retrieveDepartement(Integer idDepart) {
		log.info("Retrieving department with ID: {}", idDepart);
		Departement departement = departementRepository.findById(idDepart).orElse(null);
		if (departement != null) {
			log.info("Retrieved department: {}", departement);
		} else {
			log.warn("Department with ID: {} not found", idDepart);
		}
		return departement;
	}

	public void deleteDepartement(Integer idDepartement) {
		log.info("Deleting department with ID: {}", idDepartement);
		Departement d = retrieveDepartement(idDepartement);
		if (d != null) {
			departementRepository.delete(d);
			log.info("Deleted department: {}", d);
		} else {
			log.warn("Cannot delete. Department with ID: {} not found", idDepartement);
		}
	}
}
