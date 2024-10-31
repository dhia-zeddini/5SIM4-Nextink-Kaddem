package tn.esprit.spring.kaddem.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.kaddem.entities.Universite;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.repositories.UniversiteRepository;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)

public class UniversiteServiceImplTest {

    @Mock
    private UniversiteRepository universiteRepository;
    @Mock
    private DepartementRepository departementRepository;
    @InjectMocks
    private UniversiteServiceImpl universiteService;

    @Test
    void testRetrieveAllUniversites() {
        // Arrange
        Universite u1 = new Universite();
        Universite u2 = new Universite();
        List<Universite> universiteList = Arrays.asList(u1, u2);
        when(universiteRepository.findAll()).thenReturn(universiteList);

        // Act
        List<Universite> result = universiteService.retrieveAllUniversites();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(universiteRepository, times(1)).findAll();
    }


}
