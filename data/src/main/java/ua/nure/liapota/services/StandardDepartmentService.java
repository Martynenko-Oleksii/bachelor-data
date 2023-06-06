package ua.nure.liapota.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nure.liapota.models.security.StandardDepartment;
import ua.nure.liapota.repositories.security.StandardDepartmentRepository;

import java.util.List;

@Service
public class StandardDepartmentService extends EntityService<StandardDepartment, Integer, StandardDepartmentRepository> {

    @Autowired
    public StandardDepartmentService(StandardDepartmentRepository repository) {
        this.repository = repository;
    }

    public List<StandardDepartment> getStandardDepartments(String userId, Integer facilityId) {
        return repository.getStandardDepartments(userId, facilityId);
    }
}
