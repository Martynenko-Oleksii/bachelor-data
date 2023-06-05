package ua.nure.liapota.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nure.liapota.models.data.TimePeriodFacility;
import ua.nure.liapota.repositories.data.TimePeriodFacilityRepository;

import java.util.List;

@Service
public class TimePeriodFacilityService extends EntityService<TimePeriodFacility, Integer, TimePeriodFacilityRepository> {
    @Autowired
    public TimePeriodFacilityService(TimePeriodFacilityRepository repository) {
        this.repository = repository;
    }

    public List<TimePeriodFacility> getTimePeriodByFacilityId(Integer id) {
        return repository.getTimePeriodByFacilityId(id);
    }
}
