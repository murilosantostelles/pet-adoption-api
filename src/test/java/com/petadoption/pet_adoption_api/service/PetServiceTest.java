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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Test
    @DisplayName("Deve encontrar todos os pets cadastrados")
    void testFindAllPets(){
        List<Pet> pets = List.of(pet);
        when(petRepository.findAll()).thenReturn(pets);

        List<Pet> result = petService.findAllPets();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(petRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Deve buscar pet por id com sucesso")
    void testFindByIdSuccess(){
        when(petRepository.findById(2L)).thenReturn(Optional.of(pet));

        Pet result = petService.findById(2L);

        assertNotNull(result);
        assertEquals(2L, result.getId());
    }

    @Test
    @DisplayName("Deve lançar uma excessão ao buscar por um pet inexistente")
    void testFindByIdNotFound(){
        when(petRepository.findById(999L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> petService.findById(999L));
    }

    @Test
    @DisplayName("Deve deletar pet com sucesso")
    void testDeletePet(){
        when(petRepository.existsById(2L)).thenReturn(true);

        petService.deletePet(2L);
        verify(petRepository, times(1)).deleteById(2L);
    }

    @Test
    @DisplayName("Deve lançar excessão ao tentar deletar pet inexistente")
    void testDeletePetNotFound(){
        when(petRepository.existsById(999L)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> petService.deletePet(999L));
    }

    @Test
    @DisplayName("Deve atualizar pet com sucesso")
    void testUpdatePet(){
        Pet petData = new Pet();
        petData.setFullName("Toto Silva");
        petData.setWeight(8.0);

        when(petRepository.findById(2L)).thenReturn(Optional.of(pet));
        when(petRepository.save(any(Pet.class))).thenReturn(pet);

        Pet result = petService.updatePet(2L, petData);

        assertNotNull(result);
        verify(petRepository, times(1)).save(any(Pet.class));
    }
}