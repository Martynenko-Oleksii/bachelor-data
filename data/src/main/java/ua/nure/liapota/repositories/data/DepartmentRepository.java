package ua.nure.liapota.repositories.data;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ua.nure.liapota.models.data.Department;

import java.util.List;

public interface DepartmentRepository extends CrudRepository<Department, Integer> {
    @Query(value = "SELECT * FROM departments WHERE facility_id = ?1 AND standart_department_id = ?2", nativeQuery = true)
    List<Department> getDepartmentsByFacilityID(Integer facilityId, Integer standardDepartmentId);
}
