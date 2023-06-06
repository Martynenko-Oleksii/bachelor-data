package ua.nure.liapota.repositories.data;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ua.nure.liapota.models.data.Value;

import java.util.Set;

public interface ValueRepository extends CrudRepository<Value, Integer> {
    @Query(value = "SELECT * FROM value_table WHERE CC_account_mapping_id = ?1", nativeQuery = true)
    Set<Value> getValuesByMapping(Integer mappingId);
}
