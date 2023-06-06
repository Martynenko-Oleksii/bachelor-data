package ua.nure.liapota.models.util;

import ua.nure.liapota.models.data.ValueTypeEntity;

public class MappingTableRow {
    public MappingTableRow(int mappingId,
                           ua.nure.liapota.models.data.CostCenter costCenter,
                           ua.nure.liapota.models.data.Account account,
                           ValueTypeEntity valueType,
                           ua.nure.liapota.models.data.DepartmentElement departmentElement){
        this.mappingId = mappingId;
        this.costCenter = new CostCenter(costCenter.getNumber(),
                costCenter.getDescription(),
                costCenter.getDepartment().getId(),
                costCenter.getDepartment().getName(),
                costCenter.getDepartment().getStandardDepartmentId());
        this.account = new Account(account.getCode(), account.getDescription());
        this.valueType = new ValueType(valueType.getId(), valueType.getName());

        if (departmentElement == null) {
            this.departmentElement = null;
        } else {
            this.departmentElement = new DepartmentElement(departmentElement.getId(), departmentElement.getName());
        }
    }
    public int mappingId;
    public CostCenter costCenter;
    public Account account;
    public ValueType valueType;
    public DepartmentElement departmentElement;

    public static class CostCenter {
        public CostCenter (String number, String description, int id, String name, int standardDepartmentId) {
            this.number = number;
            this.description = description;
            this.department = new Department(id, name, standardDepartmentId);
        }

        public String number;
        public String description;
        public Department department;
        public static class Department {
            public Department(int id, String name, int standardDepartmentId) {
                this.id = id;
                this.name = name;
                this.standardDepartmentId = standardDepartmentId;
            }

            public int id;
            public String name;
            public int standardDepartmentId;
        }
    }

    public static class Account {
        public Account(String code, String description) {
            this.code = code;
            this.description = description;
        }

        public String code;
        public String description;
    }

    public static class ValueType {
        public ValueType(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int id;
        public String name;
    }

    public static class DepartmentElement {
        public DepartmentElement(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int id;
        public String name;
    }
}
