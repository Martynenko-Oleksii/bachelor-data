package ua.nure.liapota.repositories.security;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ua.nure.liapota.models.security.StandardDepartment;

import java.util.List;

public interface StandardDepartmentRepository extends CrudRepository<StandardDepartment, Integer> {

    @Query(value = "SELECT sd.*\n" +
            "FROM standart_departments sd\n" +
            "INNER JOIN facilities_standart_departments fsd ON fsd.standart_department_id = sd.standart_department_id\n" +
            "INNER JOIN facilities f ON f.facility_id = fsd.facility_id\n" +
            "INNER JOIN users_customers uc ON uc.customer_id = f.customer_id\n" +
            "WHERE uc.user_id = ?1\n" +
            "  AND f.facility_id = ?2", nativeQuery = true)
    List<StandardDepartment> getStandardDepartments(String userId, Integer facilityId);
}
