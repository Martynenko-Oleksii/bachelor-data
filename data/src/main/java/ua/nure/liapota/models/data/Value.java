package ua.nure.liapota.models.data;

import javax.persistence.*;

@Entity
@Table(name = "value_table")
public class Value {
    @Id
    @Column(name = "value_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double value;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CC_account_mapping_id")
    private GlRpMapping mapping;

    @ManyToOne
    @JoinColumn(name = "time_period_id")
    private TimePeriodFacility timePeriodFacility;

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

    public TimePeriodFacility getTimePeriodFacility() {
        return timePeriodFacility;
    }

    public void setTimePeriod(TimePeriodFacility timePeriodFacility) {
        this.timePeriodFacility = timePeriodFacility;
    }
}
