package ua.nure.liapota.repositories.data;

import org.springframework.data.repository.CrudRepository;
import ua.nure.liapota.models.data.Account;

public interface DataRepository extends CrudRepository<Account, String> {
}
