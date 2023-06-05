package ua.nure.liapota.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nure.liapota.models.data.TimePeriod;
import ua.nure.liapota.repositories.data.TimePeriodRepository;

@Service
public class TimePeriodService extends EntityService<TimePeriod, Integer, TimePeriodRepository> {
    @Autowired
    public TimePeriodService(TimePeriodRepository repository) {
        this.repository = repository;
    }

    public TimePeriod getCurrent() {
        return repository.getTimePeriodCurrent();
    }
}
