package com.petadoption.pet_adoption_api.service;

import com.petadoption.pet_adoption_api.entity.Address;
import com.petadoption.pet_adoption_api.entity.Pet;
import com.petadoption.pet_adoption_api.enums.Gender;
import com.petadoption.pet_adoption_api.enums.Type;
import com.petadoption.pet_adoption_api.repository.PetRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.awt.event.PaintEvent;
import java.util.List;


@Service
public class PetService {

    private final PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Transactional
    public Pet createPet(Pet pet){
        return petRepository.save(pet);
    }

    @Transactional
    public void deletePet(Long id){
        if(!petRepository.existsById(id)){
            throw new RuntimeException("Pet não encontrado com ID: "+id);
        }
        petRepository.deleteById(id);
    }

    @Transactional
    public Pet updatePet(Long id, Pet petData){
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pet não encontrado com ID: "+id));
        if(petData.getFullName() != null){
            pet.setFullName(petData.getFullName());
        }
        if(petData.getAge() != null){
            pet.setAge(petData.getAge());
        }
        if (petData.getWeight() != null){
            pet.setWeight(petData.getWeight());
        }
        if (petData.getRace() != null){
            pet.setRace(petData.getRace());
        }
        if(petData.getAddress() != null){
            Address address = pet.getAddress();
            if(address == null){
                address = new Address();
            }
            if(petData.getAddress().getCity() != null){
                address.setCity(petData.getAddress().getCity());
            }
            if(petData.getAddress().getStreet() != null){
                address.setStreet(petData.getAddress().getStreet());
            }
            if(petData.getAddress().getNumber() != null){
                address.setNumber(petData.getAddress().getNumber());
            }
            pet.setAddress(address);
        }
        return petRepository.save(pet);
    }

    public List<Pet> findAllPets(){
        return petRepository.findAll();
    }

    public Pet findById(Long id){
        return petRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pet não encontrado"));
    }

    public List<Pet> findByName(String name){
        return petRepository.findByFullNameContainingIgnoreCase(name);
    }

    public List<Pet> findByType(Type type){
        return petRepository.findByType(type);
    }

    public List<Pet> findByGender(Gender gender){
        return petRepository.findByGender(gender);
    }

    public List<Pet> findByAge(Double age){
        return petRepository.findByAge(age);
    }

    public List<Pet> findByWeight(Double weight){
        return petRepository.findByWeight(weight);
    }

    public List<Pet> findByRace(String race){
        return petRepository.findByRace(race);
    }

    public List<Pet> findByAddressCity(String city){
        return petRepository.findByAddressCity(city);
    }

    public List<Pet> findByAddressStreet(String street){
        return petRepository.findByAddressStreet(street);
    }

    public List<Pet> findByAddressNumber(Integer number){
        return petRepository.findByAddressNumber(number);
    }

    public List<Pet> findByAddressCityAndAddressStreet(String city, String street){
        return petRepository.findByAddressCityAndAddressStreet(city,street);
    }

    public List<Pet> findByTypeAndGender(Type type, Gender gender){
        return petRepository.findByTypeAndGender(type,gender);
    }

    public List<Pet> findByFullNameAndAge(String fullName, Double age){
        return petRepository.findByFullNameAndAge(fullName,age);
    }

    public List<Pet> findByAgeAndWeight(Double age, Double weight){
        return petRepository.findByAgeAndWeight(age, weight);
    }
}
