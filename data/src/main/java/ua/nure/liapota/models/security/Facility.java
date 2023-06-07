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
    private String abbreviation;
    @JsonIgnore
    @Column(name = "ZIP_code")
    private String zipCode;
    @JsonIgnore
    @Column(name = "address_1")
    private String address1;
    @JsonIgnore
    @Column(name = "address_2")
    private String address2;
    @JsonIgnore
    private String city;
    @JsonIgnore
    @Column(name = "contact_phone")
    private String contactPhone;
    @JsonIgnore
    @Column(name = "contact_email")
    private String contactEmail;

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

    public Set<FacilityGroup> getFacilityGroups() {
        return facilityGroups;
    }

    public void setFacilityGroups(Set<FacilityGroup> facilityGroups) {
        this.facilityGroups = facilityGroups;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
