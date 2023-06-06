package ua.nure.liapota.repositories.data;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ua.nure.liapota.models.data.TimePeriod;

public interface TimePeriodRepository extends CrudRepository<TimePeriod, Integer> {
    @Query(value = "SELECT * FROM time_periods WHERE GETDATE() BETWEEN start_date AND end_date", nativeQuery = true)
    TimePeriod getTimePeriodCurrent();
}
