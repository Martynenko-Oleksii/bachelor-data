package ua.nure.liapota.repositories.data;

import org.springframework.data.repository.CrudRepository;
import ua.nure.liapota.models.data.Value;

public interface ValueRepository extends CrudRepository<Value, Integer> {
}
