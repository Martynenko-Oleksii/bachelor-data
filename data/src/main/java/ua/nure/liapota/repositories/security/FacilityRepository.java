package ua.nure.liapota.repositories.security;

import org.springframework.data.repository.CrudRepository;
import ua.nure.liapota.models.security.Facility;

public interface FacilityRepository extends CrudRepository<Facility, Integer> {
}
