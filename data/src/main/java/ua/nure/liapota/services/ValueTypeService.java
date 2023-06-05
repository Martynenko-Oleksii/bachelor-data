package ua.nure.liapota.services;

import org.springframework.stereotype.Service;
import ua.nure.liapota.models.data.ValueTypeEntity;
import ua.nure.liapota.repositories.data.ValueTypeRepository;

@Service
public class ValueTypeService extends EntityService<ValueTypeEntity, Integer, ValueTypeRepository> {
    public ValueTypeService(ValueTypeRepository repository) {
        this.repository = repository;
    }
}
