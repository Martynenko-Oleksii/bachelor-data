package ua.nure.liapota.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nure.liapota.models.data.DepartmentElement;
import ua.nure.liapota.repositories.data.DepartmentElementRepository;

import java.util.List;

@Service
public class DepartmentElementService extends EntityService<DepartmentElement, Integer, DepartmentElementRepository> {
    @Autowired
    public DepartmentElementService(DepartmentElementRepository repository) {
        this.repository = repository;
    }

    public List<DepartmentElement> getByStandard(Integer id) {
        return repository.getDepartmentElementsByStandardDepartmentId(id);
    }
}
