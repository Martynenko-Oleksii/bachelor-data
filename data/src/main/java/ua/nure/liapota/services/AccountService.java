package ua.nure.liapota.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nure.liapota.models.data.Account;
import ua.nure.liapota.repositories.data.AccountRepository;

import java.util.List;

@Service
public class AccountService extends EntityService<Account, String, AccountRepository> {
    @Autowired
    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    public List<Account> getByFacilityId(Integer id) {
        return repository.getAccountsByFacilityID(id);
    }

    public void update(Account updatedAccount) {
        Account savedAccount = getById(updatedAccount.getCode());
        savedAccount.setDescription(updatedAccount.getDescription());
        savedAccount.setAddedBy(updatedAccount.getAddedBy());
        savedAccount.setType(updatedAccount.getType());
        savedAccount.setSource(updatedAccount.getSource());
        savedAccount.setFacilityId(updatedAccount.getFacilityId());
        repository.save(savedAccount);
    }
}
