package com.petadoption.pet_adoption_api.dto;
import com.petadoption.pet_adoption_api.enums.Gender;
import com.petadoption.pet_adoption_api.enums.Type;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PetCreateDTO {
    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 3, max = 100, message = "O Nome deve ter entre 3 e 100 caracteres")
    @Pattern(
            regexp = "^[A-Za-zÀ-ÿ]+\\s+[A-Za-zÀ-ÿ]+(\\s+[A-Za-zÀ-ÿ]+)*$",
            message = "Nome deve conter nome e sobrenome"
    )
    private String fullName;

    @NotNull(message = "Tipo é obrigatório")
    private Type type;

    @NotNull(message = "Gênero é obrigatório")
    private Gender gender;

    
    private AddressDTO address;

    @NotNull(message = "Idade é obrigatória")
    @Min(value = 0, message = "Idade não pode ser negativa")
    @Max(value = 20, message = "Idade máxima é 20 anos")
    private Double age;

    @NotNull(message = "Peso é obrigatório")
    @DecimalMin(value = "0.5", message = "Peso mínimo é 0.5kg")
    @DecimalMax(value = "60.0", message = "Peso máximo é 60.0kg")
    private Double weight;

    @Size(message = "Raça deve ter no máximo 50 caracteres")
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ\\s]*$", message = "Raça deve conter apenas letras")
    private String race;
}