package tn.esprit.spring.kaddem.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Equipe;

import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.entities.Niveau;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EquipeServiceImplTest {

    @Mock
    private EquipeRepository equipeRepository;

    @InjectMocks
    private EquipeServiceImpl equipeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRetrieveAllEquipes() {
        // Arrange
        Equipe eq1 = new Equipe();
        Equipe eq2 = new Equipe();
        List<Equipe> equipeList =  Arrays.asList(eq1, eq2);
        when(equipeRepository.findAll()).thenReturn(equipeList);
        // Act
        List<Equipe> result = equipeService.retrieveAllEquipes();

        // Assert
        assertEquals(2, result.size(), "La méthode doit retourner toutes les équipes");
        verify(equipeRepository, times(1)).findAll();
    }

    @Test
    void testAddEquipe() {
        Equipe equipe = new Equipe();
        when(equipeRepository.save(equipe)).thenReturn(equipe);

        Equipe result = equipeService.addEquipe(equipe);
        assertNotNull(result, "L'équipe ajoutée ne doit pas être nulle");
        verify(equipeRepository, times(1)).save(equipe);
    }

    @Test
    void testDeleteEquipe() {
        Equipe equipe = new Equipe();
        equipe.setIdEquipe(1);
        when(equipeRepository.findById(1)).thenReturn(Optional.of(equipe));

        equipeService.deleteEquipe(1);
        verify(equipeRepository, times(1)).delete(equipe);
    }

    @Test
    void testRetrieveEquipe() {
        Equipe equipe = new Equipe();
        equipe.setIdEquipe(1);
        when(equipeRepository.findById(1)).thenReturn(Optional.of(equipe));

        Equipe result = equipeService.retrieveEquipe(1);
        assertNotNull(result, "La méthode doit retourner l'équipe demandée");
        assertEquals(1, result.getIdEquipe(), "L'ID de l'équipe doit correspondre");
        verify(equipeRepository, times(1)).findById(1);
    }

    @Test
    void testUpdateEquipe() {
        Equipe equipe = new Equipe();
        equipe.setIdEquipe(1);
        when(equipeRepository.save(equipe)).thenReturn(equipe);

        Equipe result = equipeService.updateEquipe(equipe);
        assertNotNull(result, "L'équipe mise à jour ne doit pas être nulle");
        verify(equipeRepository, times(1)).save(equipe);
    }

}
