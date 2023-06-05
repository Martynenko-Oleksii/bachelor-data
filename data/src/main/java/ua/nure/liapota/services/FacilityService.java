package ua.nure.liapota.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nure.liapota.models.security.Facility;
import ua.nure.liapota.repositories.security.FacilityRepository;

@Service
public class FacilityService extends EntityService<Facility, Integer, FacilityRepository> {
    @Autowired
    public FacilityService(FacilityRepository repository) {
        this.repository = repository;
    }
}
