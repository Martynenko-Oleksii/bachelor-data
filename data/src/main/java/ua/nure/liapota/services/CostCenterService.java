package ua.nure.liapota.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nure.liapota.models.data.CostCenter;
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

    public void update(CostCenter updatedCostCenter) {
        CostCenter savedCostCenter = getById(updatedCostCenter.getNumber());
        savedCostCenter.setAddedBy(updatedCostCenter.getAddedBy());
        savedCostCenter.setDescription(updatedCostCenter.getDescription());
        savedCostCenter.setFacilityID(updatedCostCenter.getFacilityID());
        repository.save(savedCostCenter);
    }
}
