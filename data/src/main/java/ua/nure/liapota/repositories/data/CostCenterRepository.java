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

    @Query(value = "SELECT * FROM cost_centers " +
            "WHERE number IN (SELECT CC_number FROM GL_RP_mappings " +
            "WHERE account_code = ?1 AND valur_type_id = ?2 AND department_element_id IS NOT NULL) " +
            "AND facility_id = ?3", nativeQuery = true)
    List<CostCenter> getCostCentersByAccountCodeValueTypeIdMapped(String accountCode,
                                                                  Integer valueTypeId,
                                                                  Integer facilityId);

    @Query(value = "SELECT * FROM cost_centers " +
            "WHERE number IN (SELECT CC_number FROM GL_RP_mappings " +
            "WHERE account_code = ?1 AND valur_type_id = ?2 AND department_element_id IS NULL) " +
            "AND facility_id = ?3", nativeQuery = true)
    List<CostCenter> getCostCentersByAccountCodeValueTypeIdUnmapped(String accountCode,
                                                                    Integer valueTypeId,
                                                                    Integer facilityId);

    @Query(value = "SELECT * FROM cost_centers " +
            "WHERE number IN (SELECT CC_number FROM GL_RP_mappings " +
            "WHERE account_code = ?1 AND department_element_id IS NOT NULL) " +
            "AND facility_id = ?2", nativeQuery = true)
    List<CostCenter> getCostCentersByAccountCodeMapped(String accountCode, Integer facilityId);

    @Query(value = "SELECT * FROM cost_centers " +
            "WHERE number IN (SELECT CC_number FROM GL_RP_mappings " +
            "WHERE account_code = ?1 AND department_element_id IS NULL) " +
            "AND facility_id = ?2", nativeQuery = true)
    List<CostCenter> getCostCentersByAccountCodeUnmapped(String accountCode, Integer facilityId);

    @Query(value = "SELECT * FROM cost_centers " +
            "WHERE number IN (SELECT CC_number FROM GL_RP_mappings " +
            "WHERE valur_type_id = ?1 AND department_element_id IS NOT NULL) " +
            "AND facility_id = ?2", nativeQuery = true)
    List<CostCenter> getCostCentersByValueTypeIdMapped(Integer valueTypeId, Integer facilityId);

    @Query(value = "SELECT * FROM cost_centers " +
            "WHERE number IN (SELECT CC_number FROM GL_RP_mappings " +
            "WHERE valur_type_id = ?1 AND department_element_id IS NULL) " +
            "AND facility_id = ?2", nativeQuery = true)
    List<CostCenter> getCostCentersByValueTypeIdUnmapped(Integer valueTypeId, Integer facilityId);

    @Query(value = "SELECT * FROM cost_centers " +
            "WHERE number IN (SELECT CC_number FROM GL_RP_mappings " +
            "WHERE department_element_id IS NOT NULL) " +
            "AND facility_id = ?1", nativeQuery = true)
    List<CostCenter> getCostCentersMapped(Integer facilityId);
}
