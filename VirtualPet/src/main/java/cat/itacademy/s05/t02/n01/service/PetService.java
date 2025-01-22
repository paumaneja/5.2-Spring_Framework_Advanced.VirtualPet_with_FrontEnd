package cat.itacademy.s05.t02.n01.service;

import cat.itacademy.s05.t02.n01.dto.PetCreationDTO;
import cat.itacademy.s05.t02.n01.enums.Mood;
import cat.itacademy.s05.t02.n01.model.Pet;
import cat.itacademy.s05.t02.n01.model.User;
import cat.itacademy.s05.t02.n01.repository.PetRepository;
import cat.itacademy.s05.t02.n01.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PetService {

    private final PetRepository petRepository;
    private final UserRepository userRepository;

    @Autowired
    public PetService(PetRepository petRepository, UserRepository userRepository) {
        this.petRepository = petRepository;
        this.userRepository = userRepository;
    }

    public Pet createPet(PetCreationDTO petCreationDTO) {
        log.debug("Creating pet with name: {} and type: {} and ownerId: {}", petCreationDTO.getName(), petCreationDTO.getType(), petCreationDTO.getOwnerId());

        // Buscar l'usuari (propietari) per l'ID
        User owner = userRepository.findById(petCreationDTO.getOwnerId())
                .orElseThrow(() -> new RuntimeException("Owner not found for ID: " + petCreationDTO.getOwnerId()));

        log.debug("Owner found: {}", owner.getId());

        // Crear la mascota amb l'owner assignat
        Pet pet = Pet.builder()
                .name(petCreationDTO.getName())
                .type(petCreationDTO.getType())
                .mood(Mood.HAPPY) // Default mood
                .energy(100) // Default energy
                .weapon(null) // No weapon assigned by default
                .owner(owner) // Assignar l'owner
                .build();

        Pet savedPet = petRepository.save(pet);
        log.debug("Pet saved with ID: {}, Owner ID: {}", savedPet.getId(), savedPet.getOwner() != null ? savedPet.getOwner().getId() : null);

        return savedPet;
    }


    public List<Pet> getPetsByOwnerId(Long ownerId) {
        return petRepository.findByOwnerId(ownerId);
    }

    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    public Optional<Pet> getPetById(Long petId) {
        return petRepository.findById(petId);
    }

    public Pet updatePet(Long petId, Pet updatedPet) {
        return petRepository.findById(petId).map(pet -> {
            pet.setName(updatedPet.getName());
            pet.setType(updatedPet.getType());
            pet.setMood(updatedPet.getMood());
            pet.setEnergy(updatedPet.getEnergy());
            pet.setWeapon(updatedPet.getWeapon());
            return petRepository.save(pet);
        }).orElseThrow(() -> new IllegalArgumentException("Pet not found with id: " + petId));
    }

    public void deletePet(Long petId) {
        if (petRepository.existsById(petId)) {
            petRepository.deleteById(petId);
        } else {
            throw new IllegalArgumentException("Pet not found with id: " + petId);
        }
    }

    public Pet handleAction(Long petId, String action, String newWeapon) {
        return petRepository.findById(petId).map(pet -> {
            switch (action) {
                case "play":
                    pet.play();
                    break;
                case "sleep":
                    pet.sleep();
                    break;
                case "feed":
                    pet.feed();
                    break;
                case "changeWeapon":
                    if (pet.getType().getAllowedWeapons().contains(newWeapon)) {
                        pet.changeWeapon(newWeapon);
                    } else {
                        throw new IllegalArgumentException("Weapon not allowed for this pet type.");
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Unknown action: " + action);
            }
            return petRepository.save(pet);
        }).orElseThrow(() -> new IllegalArgumentException("Pet not found with ID: " + petId));
    }

}
