package ua.nure.liapota.repositories.data;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ua.nure.liapota.models.data.ValueTypeEntity;

import java.util.List;

public interface ValueTypeRepository extends CrudRepository<ValueTypeEntity, Integer> {
    @Query(value = "SELECT * FROM value_types WHERE name = ?1", nativeQuery = true)
    ValueTypeEntity findByName(String name);

    @Query(value = "SELECT * FROM value_types WHERE value_type_id = " +
            "(SELECT valur_type_id FROM GL_RP_mappings WHERE department_element_id IS NOT NULL)",
    nativeQuery = true)
    List<ValueTypeEntity> findMapped();
}
