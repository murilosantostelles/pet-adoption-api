package com.petadoption.pet_adoption_api.dto;

import com.petadoption.pet_adoption_api.enums.Gender;
import com.petadoption.pet_adoption_api.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PetResponseDTO {
    private Long id;
    private String fullName;
    private Type type;
    private Gender gender;
    private AddressDTO address;
    private Double age;
    private Double weight;
    private String race;
}
