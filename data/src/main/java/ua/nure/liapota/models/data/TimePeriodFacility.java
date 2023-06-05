package ua.nure.liapota.models.data;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "time_periods_facility")
public class TimePeriodFacility {
    @Id
    @Column(name = "time_period_facility_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String status;
    private int facilityId;

    @JsonIgnore
    @OneToMany(mappedBy = "timePeriodFacility")
    private Set<Value> values;

    @ManyToOne
    @JoinColumn(name = "time_period_id")
    private TimePeriod timePeriod;

    public TimePeriod getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(TimePeriod timePeriod) {
        this.timePeriod = timePeriod;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Value> getValues() {
        return values;
    }

    public void setValues(Set<Value> values) {
        this.values = values;
    }

    public int getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(int facilityId) {
        this.facilityId = facilityId;
    }
}
