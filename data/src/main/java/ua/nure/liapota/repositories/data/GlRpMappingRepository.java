package ua.nure.liapota.repositories.data;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ua.nure.liapota.models.data.GlRpMapping;

public interface GlRpMappingRepository extends CrudRepository<GlRpMapping, Integer> {
    @Query(value = "SELECT * FROM GL_RP_mappings WHERE CC_number = ?1 " +
            "AND account_code = ?2 " +
            "AND valur_type_id = ?3", nativeQuery = true)
    GlRpMapping getGlRpMappingByCostCenterAndAccountAndValueType (String CCNumber,
                                                                         String accountCode,
                                                                         Integer valueType);
}
