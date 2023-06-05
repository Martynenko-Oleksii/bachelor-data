package ua.nure.liapota.models.security;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "facility_groups")
public class FacilityGroup {
    @Id
    @Column(name = "facility_group_id")
    private int id;
    private String name;
    private String description;
    private int customerId;

    @ManyToMany
    @JoinTable(
            name = "facilities_facility_groups",
            joinColumns = @JoinColumn(name = "facility_group_id"),
            inverseJoinColumns = @JoinColumn(name = "facility_id"))
    private Set<Facility> facilities;
    @JsonIgnore
    @OneToMany(mappedBy = "facilityGroup")
    private Set<UserCustomer> usersCustomers;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Set<Facility> getFacilities() {
        return facilities;
    }

    public void setFacilities(Set<Facility> facilities) {
        this.facilities = facilities;
    }

    public Set<UserCustomer> getUsersCustomers() {
        return usersCustomers;
    }

    public void setUsersCustomers(Set<UserCustomer> usersCustomers) {
        this.usersCustomers = usersCustomers;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
