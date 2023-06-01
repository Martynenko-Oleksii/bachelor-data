package ua.nure.liapota.repositories.data;

import org.springframework.data.repository.CrudRepository;
import ua.nure.liapota.models.data.Department;

public interface DepartmentRepository extends CrudRepository<Department, Integer> {
}
