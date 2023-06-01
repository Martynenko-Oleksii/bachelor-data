package ua.nure.liapota.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nure.liapota.models.data.Department;
import ua.nure.liapota.repositories.data.DepartmentRepository;

import java.util.List;

@Service
public class DepartmentService extends EntityService<Department, Integer, DepartmentRepository> {
    @Autowired
    public DepartmentService(DepartmentRepository repository) {
        this.repository = repository;
    }

    public List<Department> getByFacilityId(Integer id) {
        return repository.getDepartmentsByFacilityID(id);
    }

    public void update(Department updatedDepartment) {
        Department savedDepartment = getById(updatedDepartment.getId());
        savedDepartment.setStandardDepartmentId(updatedDepartment.getStandardDepartmentId());
        savedDepartment.setName(updatedDepartment.getName());
        savedDepartment.setCreatedBy(updatedDepartment.getCreatedBy());
        savedDepartment.setFacilityId(updatedDepartment.getFacilityId());
        repository.save(savedDepartment);
    }
}
