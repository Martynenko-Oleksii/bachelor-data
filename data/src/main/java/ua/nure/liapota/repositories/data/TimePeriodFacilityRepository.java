package ua.nure.liapota.repositories.data;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ua.nure.liapota.models.data.TimePeriodFacility;

public interface TimePeriodFacilityRepository extends CrudRepository<TimePeriodFacility, Integer> {
    @Query(value = "SELECT * FROM time_periods_facility WHERE facility_id = ?1 AND status = 'Opened'",
            nativeQuery = true)
    TimePeriodFacility getTimePeriodByFacilityId(int facilityId);
}
