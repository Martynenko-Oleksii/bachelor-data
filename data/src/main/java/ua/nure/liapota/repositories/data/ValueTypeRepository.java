package ua.nure.liapota.repositories.data;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ua.nure.liapota.models.data.ValueTypeEntity;

public interface ValueTypeRepository extends CrudRepository<ValueTypeEntity, Integer> {
    @Query(value = "SELECT * FROM value_types WHERE name = ?1", nativeQuery = true)
    ValueTypeEntity findByName(String name);
}
