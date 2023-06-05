package ua.nure.liapota.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nure.liapota.models.security.Customer;
import ua.nure.liapota.repositories.security.CustomerRepository;

@Service
public class CustomerService extends EntityService<Customer, Integer, CustomerRepository> {
    @Autowired
    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }
}
