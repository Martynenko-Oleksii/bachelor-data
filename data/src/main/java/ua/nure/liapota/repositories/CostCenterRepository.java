package ua.nure.liapota.repositories;

import org.springframework.data.repository.CrudRepository;
import ua.nure.liapota.models.data.CostCenter;

public interface CostCenterRepository extends CrudRepository<CostCenter, String> {
}
