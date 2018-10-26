package usitcc.amarillo.theteam889.services;

import usitcc.amarillo.theteam889.entity.Pet;

import java.util.List;
import java.util.Optional;

public interface PetService {

    String createPet(Pet pet);
    void updatePet(String id, Pet pet) throws Exception;
    void deletePet(Pet pet);
    void deletePetById(Long id);
    Iterable<Pet> getAllPet();
    List<Pet> getPetByStatus(String status);
    Optional<Pet> getPetById(Long id);

}
