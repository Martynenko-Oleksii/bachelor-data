package ua.nure.liapota.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nure.liapota.models.data.CostCenter;
import ua.nure.liapota.models.util.Confirm;
import ua.nure.liapota.repositories.data.CostCenterRepository;

import java.util.List;

@Service
public class CostCenterService extends EntityService<CostCenter, String, CostCenterRepository> {
    @Autowired
    public CostCenterService(CostCenterRepository repository) {
        this.repository = repository;
    }

    public List<CostCenter> getByFacility(Integer id) {
        return repository.getCostCentersByFacilityID(id);
    }

    public List<CostCenter> getByAccountCode(Integer valueTypeId,
                                             String accountCode,
                                             Integer facilityId,
                                             boolean mapped) {
        List<CostCenter> costCenters;

        if (mapped) {
            costCenters = repository.getCostCentersByAccountCodeMapped(accountCode, valueTypeId, facilityId);
        } else {
            costCenters = repository.getCostCentersByAccountCodeUnmapped(accountCode, valueTypeId, facilityId);
        }

        return costCenters;
    }

    public void update(CostCenter updatedCostCenter) {
        CostCenter savedCostCenter = getById(updatedCostCenter.getNumber());
        savedCostCenter.setAddedBy(updatedCostCenter.getAddedBy());
        savedCostCenter.setDescription(updatedCostCenter.getDescription());
        savedCostCenter.setFacilityID(updatedCostCenter.getFacilityID());
        savedCostCenter.setDepartment(updatedCostCenter.getDepartment());
        repository.save(savedCostCenter);
    }

    public Confirm getConfirm(Integer id) {
        Confirm confirm = new Confirm();
        List<CostCenter> unmapped = repository.getCostCentersMapping(id);
        confirm.setUnmapped(unmapped);
        confirm.setCompleted(unmapped.size() == 0);
        return confirm;
    }
}
