package cat.itacademy.s05.t02.n01.service;

import cat.itacademy.s05.t02.n01.dto.PetCreationDTO;
import cat.itacademy.s05.t02.n01.enums.Mood;
import cat.itacademy.s05.t02.n01.exception.OwnerNotFoundException;
import cat.itacademy.s05.t02.n01.exception.PetNotFoundException;
import cat.itacademy.s05.t02.n01.exception.WeaponChangeNotAllowedException;
import cat.itacademy.s05.t02.n01.model.Pet;
import cat.itacademy.s05.t02.n01.model.User;
import cat.itacademy.s05.t02.n01.repository.PetRepository;
import cat.itacademy.s05.t02.n01.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    private final PetRepository petRepository;
    private final UserRepository userRepository;

    @Autowired
    public PetService(PetRepository petRepository, UserRepository userRepository) {
        this.petRepository = petRepository;
        this.userRepository = userRepository;
    }

    public Pet createPet(PetCreationDTO petCreationDTO) {
        User owner = userRepository.findById(petCreationDTO.getOwnerId())
                .orElseThrow(() -> new OwnerNotFoundException("Owner not found for ID: " + petCreationDTO.getOwnerId()));

        Pet pet = Pet.builder()
                .name(petCreationDTO.getName())
                .type(petCreationDTO.getType())
                .mood(Mood.HAPPY)
                .energy(100)
                .weapon(null)
                .owner(owner)
                .build();

        return petRepository.save(pet);
    }

    public List<Pet> getPetsByOwnerId(Long ownerId) {
        return petRepository.findByOwnerId(ownerId);
    }

    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    public Optional<Pet> getPetById(Long petId) {
        return petRepository.findById(petId).or(() -> {
            throw new PetNotFoundException("Pet not found with ID: " + petId);
        });
    }

    public Pet updatePet(Long petId, Pet updatedPet) {
        Pet pet = getPetById(petId).orElseThrow();
        pet.setName(updatedPet.getName());
        pet.setType(updatedPet.getType());
        pet.setMood(updatedPet.getMood());
        pet.setEnergy(updatedPet.getEnergy());
        pet.setWeapon(updatedPet.getWeapon());
        return petRepository.save(pet);
    }

    public void deletePet(Long petId) {
        if (petRepository.existsById(petId)) {
            petRepository.deleteById(petId);
        } else {
            throw new PetNotFoundException("Pet not found with ID: " + petId);
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
                        throw new WeaponChangeNotAllowedException("Weapon not allowed for this pet type.");
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Unknown action: " + action);
            }
            return petRepository.save(pet);
        }).orElseThrow(() -> new PetNotFoundException("Pet not found with ID: " + petId));
    }
}