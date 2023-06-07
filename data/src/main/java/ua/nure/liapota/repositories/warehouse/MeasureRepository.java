package ua.nure.liapota.repositories.warehouse;

import org.springframework.data.repository.CrudRepository;
import ua.nure.liapota.models.warehouse.Measure;

public interface MeasureRepository extends CrudRepository<Measure, Integer> {
}
