package cat.itacademy.s05.t02.n01.service;

import cat.itacademy.s05.t02.n01.model.Pet;
import cat.itacademy.s05.t02.n01.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    private final PetRepository petRepository;

    @Autowired
    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public Pet createPet(Pet pet) {
        return petRepository.save(pet);
    }

    public List<Pet> getPetsByOwnerId(Long ownerId) {
        return petRepository.findByOwnerId(ownerId);
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
}
