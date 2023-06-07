package ua.nure.liapota.models.warehouse;

import javax.persistence.*;

@Entity
@Table(name = "facts")
public class Fact {
    @EmbeddedId
    private FactKey factKey;
    private double value;

    public FactKey getFactKey() {
        return factKey;
    }

    public void setFactKey(FactKey factKey) {
        this.factKey = factKey;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
