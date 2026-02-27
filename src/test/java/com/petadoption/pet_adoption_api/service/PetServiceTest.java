package com.petadoption.pet_adoption_api.service;

import com.petadoption.pet_adoption_api.entity.Pet;
import com.petadoption.pet_adoption_api.enums.Gender;
import com.petadoption.pet_adoption_api.enums.Type;
import com.petadoption.pet_adoption_api.repository.PetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@DisplayName("Testes do PetService")
public class PetServiceTest {

    @Mock
    private PetRepository petRepository;

    @InjectMocks
    private PetService petService;

    private Pet pet;

    @BeforeEach
    void setUp(){
        pet = new Pet();
        pet.setId(2L);
        pet.setFullName("Toto Santos");
        pet.setType(Type.CACHORRO);
        pet.setGender(Gender.MACHO);
        pet.setAge(2.0);
        pet.setWeight(5.5);
        pet.setRace("Vira-lata");
    }

    @Test
    @DisplayName("Deve criar um pet com sucesso")
    void testCreatePet(){
        when(petRepository.save(any(Pet.class))).thenReturn(pet);
        Pet result = petService.createPet(pet);
        assertNotNull(result);
        assertEquals("Toto Santos", result.getFullName());
        assertEquals(2L, result.getId());
        assertEquals(Type.CACHORRO, result.getType());
        assertEquals(Gender.MACHO, result.getGender());
        assertEquals(2.0, result.getAge());
        assertEquals(5.5, result.getWeight());
        assertEquals("Vira-lata", result.getRace());
        verify(petRepository, times(1)).save(any(Pet.class));
    }
}