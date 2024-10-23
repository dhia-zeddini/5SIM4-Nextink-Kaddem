package tn.esprit.spring.kaddem.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class DepartementServiceImplTest {

	@InjectMocks
	private DepartementServiceImpl departementService;

	@Mock
	private DepartementRepository departementRepository;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testRetrieveAllDepartements() {
		// Given
		List<Departement> departements = new ArrayList<>();
		departements.add(new Departement(1, "Informatique"));
		departements.add(new Departement(2, "Mathematiques"));

		when(departementRepository.findAll()).thenReturn(departements);

		// When
		List<Departement> result = departementService.retrieveAllDepartements();

		// Then
		assertThat(result).hasSize(2);
		assertThat(result).containsAll(departements);
		verify(departementRepository, times(1)).findAll();
	}

	@Test
	public void testAddDepartement() {
		// Given
		Departement departement = new Departement("Informatique");
		Departement savedDepartement = new Departement(1, "Informatique");

		when(departementRepository.save(any(Departement.class))).thenReturn(savedDepartement);

		// When
		Departement result = departementService.addDepartement(departement);

		// Then
		assertThat(result.getIdDepart()).isEqualTo(savedDepartement.getIdDepart());
		assertThat(result.getNomDepart()).isEqualTo(savedDepartement.getNomDepart());
		verify(departementRepository, times(1)).save(departement);
	}

	@Test
	public void testUpdateDepartement() {
		// Given
		Departement departement = new Departement(1, "Informatique");

		when(departementRepository.save(any(Departement.class))).thenReturn(departement);

		// When
		Departement result = departementService.updateDepartement(departement);

		// Then
		assertThat(result.getIdDepart()).isEqualTo(departement.getIdDepart());
		assertThat(result.getNomDepart()).isEqualTo(departement.getNomDepart());
		verify(departementRepository, times(1)).save(departement);
	}

	@Test
	public void testRetrieveDepartement() {
		// Given
		Integer idDepart = 1;
		Departement departement = new Departement(idDepart, "Informatique");

		when(departementRepository.findById(idDepart)).thenReturn(Optional.of(departement));

		// When
		Departement result = departementService.retrieveDepartement(idDepart);

		// Then
		assertThat(result).isNotNull();
		assertThat(result.getIdDepart()).isEqualTo(idDepart);
		verify(departementRepository, times(1)).findById(idDepart);
	}

	@Test
	public void testDeleteDepartement() {
		// Given
		Integer idDepart = 1;
		Departement departement = new Departement(idDepart, "Informatique");

		when(departementRepository.findById(idDepart)).thenReturn(Optional.of(departement));

		// When
		departementService.deleteDepartement(idDepart);

		// Then
		verify(departementRepository, times(1)).delete(departement);
	}

	@Test
	public void testDeleteDepartementNotFound() {
		// Given
		Integer idDepart = 1;

		when(departementRepository.findById(idDepart)).thenReturn(Optional.empty());

		// When
		departementService.deleteDepartement(idDepart);

		// Then
		verify(departementRepository, never()).delete(any(Departement.class));
	}
}
