package usitcc.amarillo.theteam889.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import usitcc.amarillo.theteam889.entity.Pet;

import java.util.List;

@Repository
public interface PetRepository extends CrudRepository<Pet,Long> {

    @Query(value = "SELECT * from PET WHERE status = ?1",nativeQuery = true)
    List<Pet> getAllPetByStatus(@Param("status") String status);
}
