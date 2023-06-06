package ua.nure.liapota.repositories.data;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ua.nure.liapota.models.data.GlRpMapping;

import java.util.List;

public interface GlRpMappingRepository extends CrudRepository<GlRpMapping, Integer> {
    @Query(value = "SELECT * FROM GL_RP_mappings WHERE CC_number = ?1 " +
            "AND account_code = ?2 " +
            "AND valur_type_id = ?3", nativeQuery = true)
    GlRpMapping getGlRpMappingByCostCenterAndAccountAndValueType (String CCNumber,
                                                                         String accountCode,
                                                                         Integer valueType);

    @Query(value = "SELECT TOP 100 * FROM GL_RP_mappings WHERE department_element_id IS NULL " +
            "AND valur_type_id = ?1", nativeQuery = true)
    List<GlRpMapping> getGlRpMappingByValueTypeUnmapped(Integer valueType);

    @Query(value = "SELECT TOP 100 * FROM GL_RP_mappings WHERE department_element_id IS NOT NULL " +
            "AND valur_type_id = ?1", nativeQuery = true)
    List<GlRpMapping> getGlRpMappingByValueTypeMapped(Integer valueType);
}
