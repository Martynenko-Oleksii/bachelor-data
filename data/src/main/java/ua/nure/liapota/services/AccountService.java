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

    public List<Account> getMapped(Integer facilityId) {
        return repository.getAccountsByFacilityIDMapped(facilityId);
    }

    public List<Account> getByValueType(Integer valueTypeId, Integer facilityId, boolean mapped) {
        List<Account> accounts;

        if (mapped) {
            accounts = repository.getAccountsByValueTypeMapped(valueTypeId, facilityId);
        } else {
            accounts = repository.getAccountsByValueTypeUnmapped(valueTypeId, facilityId);
        }

        return accounts;
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
