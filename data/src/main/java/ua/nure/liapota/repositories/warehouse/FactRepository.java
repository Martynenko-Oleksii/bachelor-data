package ua.nure.liapota.repositories.warehouse;

import org.springframework.data.repository.CrudRepository;
import ua.nure.liapota.models.warehouse.Fact;
import ua.nure.liapota.models.warehouse.FactKey;

public interface FactRepository extends CrudRepository<Fact, FactKey> {
}
