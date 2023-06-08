package ua.nure.liapota.repositories.data;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ua.nure.liapota.models.data.Account;

import java.util.List;

public interface AccountRepository extends CrudRepository<Account, String> {
    @Query(value = "SELECT * FROM accounts WHERE facility_id = ?1", nativeQuery = true)
    List<Account> getAccountsByFacilityID(Integer id);

    @Query(value = "SELECT * FROM accounts " +
            "WHERE code IN (SELECT account_code FROM GL_RP_mappings " +
            "WHERE valur_type_id = ?1 AND department_element_id IS NULL) " +
            "AND facility_id = ?2", nativeQuery = true)
    List<Account> getAccountsByValueTypeUnmapped(Integer valueTypeId, Integer facilityId);

    @Query(value = "SELECT * FROM accounts " +
            "WHERE code IN (SELECT account_code FROM GL_RP_mappings " +
            "WHERE valur_type_id = ?1 AND department_element_id IS NOT NULL) " +
            "AND facility_id = ?2", nativeQuery = true)
    List<Account> getAccountsByValueTypeMapped(Integer valueTypeId, Integer facilityId);
}
