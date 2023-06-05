package ua.nure.liapota.enumeration;

public enum Column {
    COST_CENTER_NUMBER("Cost Center Number", 16, ValueType.STRING),
    COST_CENTER_DESCRIPTION("Cost Center Description", 64, ValueType.STRING),
    ACCOUNT_CODE("Account Code", 16, ValueType.STRING),
    ACCOUNT_DESCRIPTION("Account Description", 64, ValueType.STRING),
    GENERAL_LEDGER_ACCOUNT_TYPE("Type", 1, ValueType.GENERAL_LEDGER_ACCOUNT_TYPE),
    PAYROLL_ACCOUNT_TYPE("Type", 1, ValueType.PAYROLL_ACCOUNT_TYPE),
    VALUE("Value", 24, ValueType.DOUBLE),
    HOURS_WORKED("Hours Worked", 24, ValueType.DOUBLE),
    OVERTIME_HOURS("Overtime Hours", 24, ValueType.DOUBLE),
    HOURS_PAID("Hours Paid", 24, ValueType.DOUBLE),
    LABOR_EXPENSE("Labor Expense", 24, ValueType.DOUBLE),
    OVERTIME_EXPENSE("Overtime Expense", 24, ValueType.DOUBLE);


    private final String columnName;
    private final int length;
    private final ValueType valueType;

    Column(String columnName, int length, ValueType valueType) {
        this.columnName = columnName;
        this.length = length;
        this.valueType = valueType;
    }

    public String getColumnName() {
        return columnName;
    }

    public int getLength() {
        return length;
    }

    public ValueType getValueType() {
        return valueType;
    }
}
