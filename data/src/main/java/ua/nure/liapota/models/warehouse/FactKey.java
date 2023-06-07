package ua.nure.liapota.models.warehouse;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class FactKey implements Serializable {
    @Column(name = "time_period_id")
    private int timePeriodId;

    @Column(name = "facility_id")
    private int facilityId;

    @Column(name = "measure_id")
    private int measureId;

    @Column(name = "department_id")
    private int departmentId;

    public int getMeasureId() {
        return measureId;
    }

    public void setMeasureId(int measureId) {
        this.measureId = measureId;
    }

    public int getTimePeriodId() {
        return timePeriodId;
    }

    public void setTimePeriodId(int timePeriodId) {
        this.timePeriodId = timePeriodId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public int getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(int facilityId) {
        this.facilityId = facilityId;
    }
}
