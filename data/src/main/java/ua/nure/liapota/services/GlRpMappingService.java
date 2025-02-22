package ua.nure.liapota.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nure.liapota.models.data.DepartmentElement;
import ua.nure.liapota.models.data.GlRpMapping;
import ua.nure.liapota.repositories.data.GlRpMappingRepository;

import java.util.List;

@Service
public class GlRpMappingService extends EntityService<GlRpMapping, Integer, GlRpMappingRepository>{
    @Autowired
    public GlRpMappingService(GlRpMappingRepository repository) {
        this.repository = repository;
    }

    public List<GlRpMapping> getByMapping(boolean mapped) {
        List<GlRpMapping> result;

        if (mapped) {
            result = repository.getGlRpMappingMapped();
        } else {
            result = repository.getGlRpMappingUnmapped();
        }

        return result;
    }

    public List<GlRpMapping> getByCostCenterType(Integer valueType, String costCenterNumber, boolean mapped) {
        List<GlRpMapping> result;

        if (mapped) {
            result = repository.getGlRpMappingByCostCenterAndValueTypeMapped(valueType, costCenterNumber);
        } else {
            result = repository.getGlRpMappingByCostCenterAndValueTypeUnmapped(valueType, costCenterNumber);
        }

        return result;
    }

    public List<GlRpMapping> getByAccount(String accountCode, boolean mapped) {
        List<GlRpMapping> result;

        if (mapped) {
            result = repository.getGlRpMappingByAccountMapped(accountCode);
        } else {
            result = repository.getGlRpMappingByAccountUnmapped(accountCode);
        }

        return result;
    }

    public List<GlRpMapping> getByCostCenterAccount(String accountCode, String costCenter, boolean mapped) {
        List<GlRpMapping> result;

        if (mapped) {
            result = repository.getGlRpMappingByAccountAndCostCenterMapped(accountCode, costCenter);
        } else {
            result = repository.getGlRpMappingByAccountAndCostCenterUnmapped(accountCode, costCenter);
        }

        return result;
    }

    public List<GlRpMapping> getByCostCenter(String costCenterNumber, boolean mapped) {
        List<GlRpMapping> result;

        if (mapped) {
            result = repository.getGlRpMappingByCostCenterMapped(costCenterNumber);
        } else {
            result = repository.getGlRpMappingByCostCenterUnmapped(costCenterNumber);
        }

        return result;
    }

    public List<GlRpMapping> getByValueType(Integer valueTypeId, boolean mapped) {
        List<GlRpMapping> result;

        if (mapped) {
            result = repository.getGlRpMappingByValueTypeMapped(valueTypeId);
        } else {
            result = repository.getGlRpMappingByValueTypeUnmapped(valueTypeId);
        }

        return result;
    }

    public List<GlRpMapping> getByAccountType(Integer valueTypeId, String accountCode, boolean mapped) {
        List<GlRpMapping> result;

        if (mapped) {
            result = repository.getGlRpMappingByValueTypeAndAccountMapped(valueTypeId, accountCode);
        } else {
            result = repository.getGlRpMappingByValueTypeAndAccountUnmapped(valueTypeId, accountCode);
        }

        return result;
    }

    public List<GlRpMapping> getByCostCenterAccountType(Integer valueTypeId,
                                                        String accountCode,
                                                        String costCenterName,
                                                        boolean mapped) {
        List<GlRpMapping> result;

        if (mapped) {
            result = repository.getGlRpMappingByValueTypeAndAccountAndCostCenterMapped(valueTypeId,
                    accountCode,
                    costCenterName);
        } else {
            result = repository.getGlRpMappingByValueTypeAndAccountAndCostCenterUnmapped(valueTypeId,
                    accountCode,
                    costCenterName);
        }

        return result;
    }

    public void update(GlRpMapping mapping, DepartmentElement departmentElement) {
        mapping.setDepartmentElement(departmentElement);
        repository.save(mapping);
    }
}
