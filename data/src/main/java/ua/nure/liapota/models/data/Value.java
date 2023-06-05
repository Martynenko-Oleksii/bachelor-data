package ua.nure.liapota.models.data;

import javax.persistence.*;

@Entity
@Table(name = "values")
public class Value {
    @Id
    @Column(name = "value_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double value;

    @ManyToOne
    @JoinColumn(name = "CC_account_mapping_id")
    private GlRpMapping mapping;

    @ManyToOne
    @JoinColumn(name = "time_period_id")
    private TimePeriodFacility timePeriod;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public GlRpMapping getMapping() {
        return mapping;
    }

    public void setMapping(GlRpMapping mapping) {
        this.mapping = mapping;
    }

    public TimePeriodFacility getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(TimePeriodFacility timePeriod) {
        this.timePeriod = timePeriod;
    }
}
