package com.petadoption.pet_adoption_api.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PetUpdateDTO {
    @Size(min = 3, max = 100, message = "O Nome deve ter entre 3 e 100 caracteres")
    @NotBlank(message = "Nome não pode estar vazio")
    private String fullName;

    private AddressDTO address;

    @Min(value = 0, message = "Idade não pode ser negativa")
    @Max(value = 20, message = "Idade máxima é 20 anos")
    private Double age;

    @DecimalMin(value = "0.5", message = "Peso mínimo é 0.5kg")
    @DecimalMax(value = "60.0", message = "Peso máximo é 60.0kg")
    private Double weight;

    @Size(message = "Raça deve ter no máximo 50 caracteres")
    private String race;
}
