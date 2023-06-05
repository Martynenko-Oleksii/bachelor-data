package ua.nure.liapota.models.data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "time_periods")
public class TimePeriod {
    @Id
    @Column(name = "time_period_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "short_name")
    private String shortName;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "end_date")
    private Date endDate;

    @OneToMany(mappedBy = "timePeriods")
    private Set<TimePeriodFacility> timePeriodFacilitySet;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Set<TimePeriodFacility> getTimePeriodFacilitySet() {
        return timePeriodFacilitySet;
    }

    public void setTimePeriodFacilitySet(Set<TimePeriodFacility> timePeriodFacilitySet) {
        this.timePeriodFacilitySet = timePeriodFacilitySet;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
}
