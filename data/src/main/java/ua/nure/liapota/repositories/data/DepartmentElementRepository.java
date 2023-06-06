package ua.nure.liapota.repositories.data;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ua.nure.liapota.models.data.DepartmentElement;

import java.util.List;

public interface DepartmentElementRepository extends CrudRepository<DepartmentElement, Integer> {
    @Query(value = "SELECT * FROM department_elements WHERE standart_department_id = ?1", nativeQuery = true)
    List<DepartmentElement> getDepartmentElementsByStandardDepartmentId(Integer id);
}
