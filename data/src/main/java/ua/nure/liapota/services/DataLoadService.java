package ua.nure.liapota.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nure.liapota.models.data.*;
import ua.nure.liapota.enumeration.Column;
import ua.nure.liapota.repositories.data.AccountRepository;
import ua.nure.liapota.repositories.data.CostCenterRepository;
import ua.nure.liapota.repositories.data.ValueTypeRepository;

import java.util.*;

@Service
public class DataLoadService extends GlRpMappingService{
    private final CostCenterRepository costCenterRepository;
    private final AccountRepository accountRepository;
    private final ValueTypeRepository valueTypeRepository;
    private TimePeriodFacility timePeriod;

    @Autowired
    public DataLoadService(CostCenterRepository costCenterRepository,
                           AccountRepository accountRepository,
                           ValueTypeRepository valueTypeRepository) {
        this.costCenterRepository = costCenterRepository;
        this.accountRepository = accountRepository;
        this.valueTypeRepository = valueTypeRepository;
    }

    public void loadPayrollData(Map<Integer, String> mapping,
                                             String[][] fileToArray,
                                             TimePeriodFacility timePeriod) {
        this.timePeriod = timePeriod;

        for (String[] strings : fileToArray) {
            List<GlRpMapping> rpMappings = new ArrayList<>();
            GlRpMapping localRpMapping = new GlRpMapping();

            for (Map.Entry<Integer, String> entry : mapping.entrySet()) {
                int key = entry.getKey();
                String value = entry.getValue();
                if(constructPrMappings(strings[key - 1], value, localRpMapping)) {
                    rpMappings.add(localRpMapping);
                    localRpMapping.setValues(null);
                }
            }

            for (GlRpMapping m : rpMappings) {
                localRpMapping.setValues(m.getValues());
                localRpMapping.setValueType(m.getValueType());
                saveMapping(localRpMapping);
            }
        }
    }

    private boolean constructPrMappings(String value, String mappedValue, GlRpMapping prMapping) {
        boolean needNew = false;

        if (mappedValue.equals(Column.COST_CENTER_NUMBER.getColumnName())) {
            prMapping.setCostCenter(costCenterRepository.findById(value).get());
        } else if (mappedValue.equals(Column.ACCOUNT_CODE.getColumnName())) {
            prMapping.setAccount(accountRepository.findById(value).get());
        } else if (mappedValue.equals(Column.HOURS_WORKED.getColumnName())
                || mappedValue.equals(Column.OVERTIME_HOURS.getColumnName())
                || mappedValue.equals(Column.HOURS_PAID.getColumnName())
                || mappedValue.equals(Column.LABOR_EXPENSE.getColumnName())
                || mappedValue.equals(Column.OVERTIME_EXPENSE.getColumnName())) {
            setValue(value, prMapping);
            ValueTypeEntity valueType = valueTypeRepository.findByName(mappedValue);
            prMapping.setValueType(valueType);
            needNew = true;
        }

        return needNew;
    }

    private void setValue(String value, GlRpMapping mapping) {
        Value valueEntity = new Value();
        valueEntity.setValue(Double.parseDouble(value));
        valueEntity.setTimePeriod(timePeriod);
        Set<Value> values = (mapping.getValues() == null) ? new HashSet<>() : mapping.getValues();
        values.add(valueEntity);
        mapping.setValues(values);
    }

    public void loadGeneralLedgerData(Map<Integer, String> mapping,
                                      String[][] fileToArray,
                                      TimePeriodFacility timePeriod) {
        this.timePeriod = timePeriod;
        for (String[] strings : fileToArray) {
            GlRpMapping localGlMapping = new GlRpMapping();

            for (Map.Entry<Integer, String> entry : mapping.entrySet()) {
                int key = entry.getKey();
                String value = entry.getValue();
                constructGlMappings(strings[key - 1], value, localGlMapping);
            }

            saveMapping(localGlMapping);
        }
    }

    private void saveMapping(GlRpMapping localGlMapping) {
        GlRpMapping repositoryGlMapping = repository.getGlRpMappingByCostCenterAndAccountAndValueType(
                localGlMapping.getCostCenter().getNumber(),
                localGlMapping.getAccount().getCode(),
                localGlMapping.getValueType().getId());

        if (repositoryGlMapping == null) {
            repository.save(localGlMapping);
        } else {
            Set<Value> repositorySet = repositoryGlMapping.getValues();
            repositorySet.addAll(localGlMapping.getValues());
            repositoryGlMapping.setValues(repositorySet);
            repository.save(repositoryGlMapping);
        }
    }

    private void constructGlMappings(String value, String mappedValue, GlRpMapping glMapping) {
        if (mappedValue.equals(Column.COST_CENTER_NUMBER.getColumnName())) {
            glMapping.setCostCenter(costCenterRepository.findById(value).get());
        } else if (mappedValue.equals(Column.ACCOUNT_CODE.getColumnName())) {
            Account account = accountRepository.findById(value).get();
            ValueTypeEntity valueType = valueTypeRepository.findByName(getTypeByAccount(account.getType()));
            glMapping.setAccount(account);
            glMapping.setValueType(valueType);
        } else if (mappedValue.equals(Column.VALUE.getColumnName())) {
            setValue(value, glMapping);
        }
    }

    private String getTypeByAccount(char type) {
        String result = "";
        switch (type) {
            case ('E') :
                result = "Expenses";
                break;
            case ('R') :
                result = "Revenues";
                break;
            case ('V') :
                result = "Volumes ";
                break;
            case ('A') :
                result = "Assets";
                break;
            case ('L') :
                result = "Liabilities";
                break;
            case ('I') :
                result = "Income";
                break;
            case ('P') :
                result = "Physician";
                break;
            case ('N') :
                result = "Non-Physician";
                break;
            case ('C') :
                result = "Contract";
                break;
        }
        return result;
    }

    public List<CostCenter> getCostCentersFromMapping(Map<Integer, String> mapping,
                                                      String[][] fileToArray,
                                                      CostCenter costCenter) {
        List<CostCenter> costCenters = new ArrayList<>();

        for (String[] strings : fileToArray) {
            CostCenter localCostCenter = new CostCenter();
            localCostCenter.setFacilityID(costCenter.getFacilityID());
            localCostCenter.setAddedBy(costCenter.getAddedBy());

            for (Map.Entry<Integer, String> entry : mapping.entrySet()) {
                int key = entry.getKey();
                String value = entry.getValue();
                constructCostCenter(strings[key - 1], value, localCostCenter);
            }

            costCenters.add(localCostCenter);
        }

        return costCenters;
    }

    private void constructCostCenter(String value, String mappedValue, CostCenter costCenter) {
        if (mappedValue.equals(Column.COST_CENTER_NUMBER.getColumnName())) {
            costCenter.setNumber(value);
        } else if (mappedValue.equals(Column.COST_CENTER_DESCRIPTION.getColumnName())) {
            costCenter.setDescription(value);
        }
    }

    public List<Account> getAccountsFromMapping(Map<Integer, String> mapping,
                                                             String[][] fileToArray,
                                                             Account account) {
        List<Account> accounts = new ArrayList<>();

        for (String[] strings : fileToArray) {
            Account localAccount = new Account();
            localAccount.setFacilityId(account.getFacilityId());
            localAccount.setAddedBy(account.getAddedBy());

            for (Map.Entry<Integer, String> entry : mapping.entrySet()) {
                int key = entry.getKey();
                String value = entry.getValue();
                constructAccount(strings[key - 1], value, localAccount);
            }

            accounts.add(localAccount);
        }

        return accounts;
    }

    private void constructAccount(String value, String mappedValue, Account account) {
        if (mappedValue.equals(Column.ACCOUNT_CODE.getColumnName())) {
            account.setCode(value);
        } else if (mappedValue.equals(Column.ACCOUNT_DESCRIPTION.getColumnName())) {
            account.setDescription(value);
        } else if (mappedValue.equals(Column.PAYROLL_ACCOUNT_TYPE.getColumnName()) ||
                mappedValue.equals(Column.GENERAL_LEDGER_ACCOUNT_TYPE.getColumnName())) {
            account.setType(value.charAt(0));
        }
    }
}
