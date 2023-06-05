package ua.nure.liapota.models.security;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "facilities")
public class Facility {
    @Id
    @Column(name = "facility_id")
    private int id;
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "facilities")
    private Set<FacilityGroup> facilityGroups;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
