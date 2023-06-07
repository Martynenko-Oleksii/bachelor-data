package ua.nure.liapota.repositories.warehouse;

import org.springframework.data.repository.CrudRepository;
import ua.nure.liapota.models.warehouse.DepartmentInstanceWarehouse;

public interface DepartmentInstanceRepository extends CrudRepository<DepartmentInstanceWarehouse, Integer> {
}
