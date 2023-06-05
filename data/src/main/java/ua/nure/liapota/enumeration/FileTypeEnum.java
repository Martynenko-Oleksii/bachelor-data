package ua.nure.liapota.enumeration;

public enum FileTypeEnum {
    COST_CENTER_LIST("Cost Center List"),
    GENERAL_LEDGER_ACCOUNTS_LIST("General Ledger Accounts List"),
    PAYROLL_ACCOUNTS_LIST("Payroll Accounts List"),
    GENERAL_LEDGER_DATA("General Ledger Data"),
    PAYROLL_DATA("Payroll Data");

    private final String fileTypeName;

    FileTypeEnum(String fileTypeName) {
        this.fileTypeName = fileTypeName;
    }

    public String getFileTypeName() {
        return fileTypeName;
    }
}
