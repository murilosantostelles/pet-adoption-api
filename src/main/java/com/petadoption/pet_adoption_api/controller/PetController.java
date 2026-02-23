package com.petadoption.pet_adoption_api.controller;

import com.petadoption.pet_adoption_api.dto.AddressDTO;
import com.petadoption.pet_adoption_api.dto.PetCreateDTO;
import com.petadoption.pet_adoption_api.dto.PetResponseDTO;
import com.petadoption.pet_adoption_api.dto.PetUpdateDTO;
import com.petadoption.pet_adoption_api.entity.Address;
import com.petadoption.pet_adoption_api.entity.Pet;
import com.petadoption.pet_adoption_api.enums.Gender;
import com.petadoption.pet_adoption_api.enums.Type;
import com.petadoption.pet_adoption_api.service.PetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pets")
@Tag(name= "Pets", description = "Gerenciamento de pets para adoção")
public class PetController {
    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping
    @Operation(summary = "Cadastrar novo pet", description = "Registra um novo pet no sistema para disponibilizar para adoção")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pet cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao cadastrar Pet. Dados inválidos foram fornecidos")
    })
    public ResponseEntity<PetResponseDTO> createPet(@Valid @RequestBody PetCreateDTO dto) {
        Pet pet = toEntity(dto);
        Pet savedPet = petService.createPet(pet);
        PetResponseDTO responseDTO = toResponseDTO(savedPet);
        return ResponseEntity.status(201).body(responseDTO);
    }

    @GetMapping
    @Operation(summary = "Listar todos os pets", description = "Retorna a lista completa de pets cadastrados no sistema")
    @ApiResponse(responseCode = "200", description = "Lista de pets retornada com sucesso")
    public ResponseEntity<List<PetResponseDTO>> getAllPets() {
        List<Pet> pets = petService.findAllPets();
        List<PetResponseDTO> responseDTO = pets.stream()
                .map(this::toResponseDTO)
                .toList();
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar pet por ID", description = "Retorna os dados de um pet específico por meio de seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pet econtrado"),
            @ApiResponse(responseCode = "404", description = "Pet não encontrado")
    })
    public ResponseEntity<PetResponseDTO> getPetById(@PathVariable Long id) {
        Pet pet = petService.findById(id);
        PetResponseDTO responseDTO = toResponseDTO(pet);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/search")
    @Operation(summary = "Buscar pets por critérios", description = "Filtra os pets com um ou mais critérios de busca")
    @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso")
    public ResponseEntity<List<PetResponseDTO>> searchPets(@RequestParam(required = false) String fullName,
                                                           @RequestParam(required = false) Type type,
                                                           @RequestParam(required = false) Gender gender,
                                                           @RequestParam(required = false) Double age,
                                                           @RequestParam(required = false) Double weight,
                                                           @RequestParam(required = false) String race,
                                                           @RequestParam(required = false) String city,
                                                           @RequestParam(required = false) String street,
                                                           @RequestParam(required = false) Integer number){
        List<Pet> pets;

        if (fullName != null && age != null) {
            pets = petService.findByFullNameAndAge(fullName, age);
        }
        else if(fullName != null){
            pets = petService.findByName(fullName);
        } else if (type != null && gender != null) {
            pets = petService.findByTypeAndGender(type,gender);
        } else if (age != null && weight != null) {
            pets = petService.findByAgeAndWeight(age, weight);
        }
        else if (city != null && street != null) {
            pets = petService.findByAddressCityAndAddressStreet(city, street);
        } else if (age != null) {
            pets = petService.findByAge(age);
        } else if (weight != null) {
            pets = petService.findByWeight(weight);
        } else if (race != null) {
            pets = petService.findByRace(race);
        } else if (city != null) {
            pets = petService.findByAddressCity(city);
        } else if (gender != null) {
            pets = petService.findByGender(gender);

        } else if (street != null) {
            pets = petService.findByAddressStreet(street);
        } else if (number != null) {
            pets = petService.findByAddressNumber(number);
        } else {
            pets = petService.findAllPets();
        }
        List<PetResponseDTO> responseDTO = pets.stream()
                .map(this::toResponseDTO)
                .toList();

        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar dados do pet", description = "Atualiza informações de um pet existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pet atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pet não encontrado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public ResponseEntity<PetResponseDTO> updatePet(@PathVariable Long id, @Valid @RequestBody PetUpdateDTO dto){
        Pet petData = toEntityFromUpdate(dto);
        Pet updatedPet = petService.updatePet(id, petData);
        PetResponseDTO responseDTO = toResponseDTO(updatedPet);

        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover pet", description = "Remove um pet anteriormente cadastrado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Pet removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pet não encontrado")
    })
    public ResponseEntity<Void> deletePet(@PathVariable Long id){
        petService.deletePet(id);
        return ResponseEntity.noContent().build();
    }

    //Conversões de DTO pra Entity e vice versa.
    private Pet toEntity(PetCreateDTO dto) {
        Pet pet = new Pet();
        pet.setFullName(dto.getFullName());
        pet.setType(dto.getType());
        pet.setGender(dto.getGender());
        pet.setAge(dto.getAge());
        pet.setWeight(dto.getWeight());
        pet.setRace(dto.getRace());
        if (dto.getAddress() != null) {
            Address address = new Address();
            address.setCity(dto.getAddress().getCity());
            address.setStreet(dto.getAddress().getStreet());
            address.setNumber(dto.getAddress().getNumber());
            pet.setAddress(address);
        }
        return pet;
    }

    private Pet toEntityFromUpdate(PetUpdateDTO dto) {
        Pet pet = new Pet();
        pet.setFullName(dto.getFullName());
        pet.setAge(dto.getAge());
        pet.setWeight(dto.getWeight());
        pet.setRace(dto.getRace());
        if (dto.getAddress() != null) {
            Address address = new Address();
            address.setStreet(dto.getAddress().getStreet());
            address.setCity(dto.getAddress().getCity());
            address.setNumber(dto.getAddress().getNumber());
            pet.setAddress(address);
        }
        return pet;
    }

    private PetResponseDTO toResponseDTO(Pet pet) {
        PetResponseDTO dto = new PetResponseDTO();
        dto.setId(pet.getId());
        dto.setFullName(pet.getFullName());
        dto.setType(pet.getType());
        dto.setGender(pet.getGender());
        dto.setAge(pet.getAge());
        dto.setWeight(pet.getWeight());
        dto.setRace(pet.getRace());
        if (pet.getAddress() != null) {
            AddressDTO addressDTO = new AddressDTO();
            addressDTO.setCity(pet.getAddress().getCity());
            addressDTO.setStreet(pet.getAddress().getStreet());
            addressDTO.setNumber(pet.getAddress().getNumber());
            dto.setAddress(addressDTO);
        }
        return dto;
    }
}
