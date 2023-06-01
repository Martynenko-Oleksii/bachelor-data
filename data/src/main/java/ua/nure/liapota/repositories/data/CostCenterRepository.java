package ua.nure.liapota.repositories.data;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ua.nure.liapota.models.data.CostCenter;

import java.util.List;

public interface CostCenterRepository extends CrudRepository<CostCenter, String> {
    @Query(value = "SELECT * FROM cost_centers WHERE facility_id = ?1", nativeQuery = true)
    List<CostCenter> getCostCentersByFacilityID(Integer id);

    @Query(value = "SELECT * FROM cost_centers WHERE facility_id = ?1 AND department_id IS NULL", nativeQuery = true)
    List<CostCenter> getCostCentersMapping(Integer id);
}
