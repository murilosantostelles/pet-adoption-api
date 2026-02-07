package com.petadoption.pet_adoption_api.service;

import com.petadoption.pet_adoption_api.entity.Pet;
import com.petadoption.pet_adoption_api.repository.PetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    private final PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public List<Pet> findAllPets(){
        return petRepository.findAll();
    }

    public Pet createPet(Pet pet){
        return petRepository.save(pet);
    }

    public void deletePet(Long id){
        petRepository.deleteById(id);
    }
}
