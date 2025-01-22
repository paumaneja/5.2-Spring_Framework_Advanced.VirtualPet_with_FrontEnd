package cat.itacademy.s05.t02.n01.controller;

import cat.itacademy.s05.t02.n01.dto.PetCreationDTO;
import cat.itacademy.s05.t02.n01.dto.PetDTO;
import cat.itacademy.s05.t02.n01.model.Pet;
import cat.itacademy.s05.t02.n01.service.PetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pets")
@Slf4j
public class PetController {

    private final PetService petService;

    @Autowired
    public PetController(PetService petService) {
        this.petService = petService;
    }


    @PostMapping("/create")
    public ResponseEntity<PetDTO> createPet(@RequestBody PetCreationDTO petCreationDTO) {
        Pet createdPet = petService.createPet(petCreationDTO);
        PetDTO petDTO = PetDTO.builder()
                .id(createdPet.getId())
                .name(createdPet.getName())
                .type(createdPet.getType())
                .mood(createdPet.getMood())
                .energy(createdPet.getEnergy())
                .ownerId(createdPet.getOwner() != null ? createdPet.getOwner().getId() : null)
                .build();
        return ResponseEntity.ok(petDTO);
    }


    @GetMapping("/getPetsByOwner")
    public ResponseEntity<List<PetDTO>> getPetsByOwnerId(@RequestParam Long ownerId) {
        List<PetDTO> pets = petService.getPetsByOwnerId(ownerId).stream()
                .map(pet -> PetDTO.builder()
                        .id(pet.getId())
                        .name(pet.getName())
                        .type(pet.getType())
                        .mood(pet.getMood())
                        .energy(pet.getEnergy())
                        .weapon(pet.getWeapon())
                        .ownerId(pet.getOwner() != null ? pet.getOwner().getId() : null)
                        .build())
                .collect(Collectors.toList());
        return ResponseEntity.ok(pets);
    }

    @GetMapping("/getAllPets")
    public ResponseEntity<List<PetDTO>> getAllPets() {
        log.debug("Rebuda sol·licitud GET /getAllPets");
        List<PetDTO> pets = petService.getAllPets().stream()
                .map(pet -> PetDTO.builder()
                        .id(pet.getId())
                        .name(pet.getName())
                        .type(pet.getType())
                        .mood(pet.getMood())
                        .energy(pet.getEnergy())
                        .weapon(pet.getWeapon())
                        .ownerId(pet.getOwner() != null ? pet.getOwner().getId() : null)
                        .build())
                .collect(Collectors.toList());

        // Afegir un log per veure el contingut
        log.debug("Mascotes retornades pel controlador: {}", pets);

        return ResponseEntity.ok(pets);
    }

    @GetMapping("/getPet/{id}")
    public ResponseEntity<Pet> getPetById(@PathVariable Long id) {
        return petService.getPetById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PutMapping("/{id}")
    public ResponseEntity<Pet> updatePet(@PathVariable Long id, @RequestParam String action, @RequestParam(required = false) String newWeapon) {
        try {
            Pet updatedPet = petService.handleAction(id, action, newWeapon);
            return ResponseEntity.ok(updatedPet);
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().build();
        }
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePet(@PathVariable Long id) {
        try {
            petService.deletePet(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
