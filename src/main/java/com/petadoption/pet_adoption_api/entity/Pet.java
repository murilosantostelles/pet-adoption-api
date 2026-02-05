package com.petadoption.pet_adoption_api.entity;

import com.petadoption.pet_adoption_api.enums.Gender;
import com.petadoption.pet_adoption_api.enums.Type;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "pets")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    @NotBlank(message = "Nome é Obrigatório")
    @Size(min = 3, max = 100)
    private String fullName;

    @Column(nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    @NotNull
    private Type type;

    @Column(nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    @NotNull
    private Gender gender;

    @Embedded
    private Address address;

    @Column(nullable = false)
    @Min(value = 0, message = "Idade não pode ser negativa")
    @Max(value = 20, message = "Idade não pode ser maior que 20 anos")
    private Double age;

    @Column(nullable = false)
    @DecimalMin(value = "0.5", message = "Peso não pode ser menor que 0.5kg")
    @DecimalMax(value = "60.0", message = "Peso não pode ser maior que 60.0kg")
    private Double weight;

    @Column(length = 50)
    @Size(max = 50)
    private String race;
}
