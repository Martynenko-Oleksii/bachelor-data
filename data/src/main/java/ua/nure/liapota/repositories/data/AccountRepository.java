package ua.nure.liapota.repositories.data;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ua.nure.liapota.models.data.Account;

import java.util.List;

public interface AccountRepository extends CrudRepository<Account, String> {
    @Query(value = "SELECT * FROM accounts WHERE facility_id = ?1", nativeQuery = true)
    List<Account> getAccountsByFacilityID(Integer id);
}
