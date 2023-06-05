package ua.nure.liapota.repositories.security;

import org.springframework.data.repository.CrudRepository;
import ua.nure.liapota.models.security.UserCustomer;

public interface UserCustomerRepository extends CrudRepository<UserCustomer, String> {
}
