package ua.nure.liapota.enumeration;

public enum ValueType {
    DOUBLE(""),
    STRING(""),
    GENERAL_LEDGER_ACCOUNT_TYPE("ERVALI"),
    PAYROLL_ACCOUNT_TYPE("PNC");

    private final String code;

    ValueType(String code) {
        this.code = code;
    }

    public char[] getCharArrayValue() {
        return code.toCharArray();
    }
}
