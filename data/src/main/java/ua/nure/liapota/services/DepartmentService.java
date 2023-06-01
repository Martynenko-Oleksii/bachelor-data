package ua.nure.liapota.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nure.liapota.models.data.Department;
import ua.nure.liapota.repositories.data.DepartmentRepository;

@Service
public class DepartmentService extends EntityService<Department, Integer, DepartmentRepository> {
    @Autowired
    public DepartmentService(DepartmentRepository repository) {
        this.repository = repository;
    }
}
