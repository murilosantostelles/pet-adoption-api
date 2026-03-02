package com.petadoption.pet_adoption_api.repository;

import com.petadoption.pet_adoption_api.entity.Address;
import com.petadoption.pet_adoption_api.entity.Pet;
import com.petadoption.pet_adoption_api.enums.Gender;
import com.petadoption.pet_adoption_api.enums.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {

    List<Pet> findByFullNameContainingIgnoreCase(String name);

    List<Pet> findByGender(Gender gender);

    List<Pet> findByType(Type type);

    List<Pet> findByAge(Double age);

    List<Pet> findByWeight(Double weight);

    List<Pet> findByRace(String race);

    List<Pet> findByAddressCity(String city);

    List<Pet> findByAddressStreet(String street);

    List<Pet> findByAddressNumber(Integer number);

    List<Pet> findByAddressCityAndAddressStreet(String city, String street);

    List<Pet> findByTypeAndGender(Type type, Gender gender);

    List<Pet> findByFullNameAndAge(String fullName, Double age);

    List<Pet> findByAgeAndWeight(Double age, Double weight);

}
