package usitcc.amarillo.theteam889.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import usitcc.amarillo.theteam889.entity.Pet;
import usitcc.amarillo.theteam889.repository.PetRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PetServiceImpl implements PetService {

    @Autowired
    PetRepository petRepository;


    @Override
    public String createPet(Pet pet) {
        boolean mybool = false;
        try {
            petRepository.save(pet);
        }catch(Exception e){
            mybool = true;
        }
        return mybool?"Failed.":"Successful.";
    }

    @Override
    public void updatePet(String id, Pet pet) throws Exception {
        Optional<Pet> pr = petRepository.findById(pet.getId());
        if(pr.isPresent()){
            pr.get().setSpecies(pet.getSpecies());
            pr.get().setAge(pet.getAge());
            pr.get().setPrice(pet.getPrice());
            pr.get().setDiscount(pet.getDiscount());
            pr.get().setFedday(pet.getFedday());
            pr.get().setStatus(pet.getStatus());
            petRepository.save(pr.get());
        }else{
            //implement the Generic Exception
            throw new Exception("Pet Not Found!");
        }

    }

    @Override
    public void deletePet(Pet transaction) {
        petRepository.delete(transaction);
    }

    @Override
    public void deletePetById(Long id) {
        petRepository.deleteById(id);
    }

    @Override
    public Iterable<Pet> getAllPet() {
        return petRepository.findAll();
    }

    @Override
    public List<Pet> getPetByStatus(String status) {
        return petRepository.getAllPetByStatus(status);
    }


    @Override
    public Optional<Pet> getPetById(Long id) {
        return petRepository.findById(id);
    }
}
