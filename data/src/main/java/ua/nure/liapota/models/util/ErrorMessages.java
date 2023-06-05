package ua.nure.liapota.models.util;

public class ErrorMessages {
    private final String[] columnValue;
    private final String[] columnError;

    public ErrorMessages(String[] columnError, String[] columnValue) {
        this.columnValue = columnValue;
        this.columnError = columnError;
    }

    public String[] getColumnError() {
        return columnError;
    }

    public String[] getColumnValue() {
        return columnValue;
    }
}
