package ua.nure.liapota.models.util;

import ua.nure.liapota.models.data.CostCenter;

import java.util.List;


public class Confirm {
    private boolean completed;
    private List<CostCenter> unmapped;

    public List<CostCenter> getUnmapped() {
        return unmapped;
    }

    public void setUnmapped(List<CostCenter> unmapped) {
        this.unmapped = unmapped;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
