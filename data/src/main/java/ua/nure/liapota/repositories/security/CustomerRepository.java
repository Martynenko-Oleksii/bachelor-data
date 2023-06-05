package ua.nure.liapota.repositories.security;

import org.springframework.data.repository.CrudRepository;
import ua.nure.liapota.models.security.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
}
