package ua.nure.liapota.models.security;

import javax.persistence.*;

@Entity
@Table(name = "facilities")
public class Facility {
    @Id
    @Column(name = "facility_id")
    private int id;
    private String name;

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
