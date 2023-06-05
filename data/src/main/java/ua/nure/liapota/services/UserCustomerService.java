package ua.nure.liapota.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nure.liapota.models.security.UserCustomer;
import ua.nure.liapota.repositories.security.UserCustomerRepository;

@Service
public class UserCustomerService extends EntityService<UserCustomer, String, UserCustomerRepository> {
    @Autowired
    public UserCustomerService(UserCustomerRepository repository) {
        this.repository = repository;
    }
}
