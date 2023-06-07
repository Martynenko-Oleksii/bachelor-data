package ua.nure.liapota.repositories.warehouse;

import org.springframework.data.repository.CrudRepository;
import ua.nure.liapota.models.warehouse.TimePeriodWarehouse;

public interface TimePeriodWarehouseRepository extends CrudRepository<TimePeriodWarehouse, Integer> {
}
