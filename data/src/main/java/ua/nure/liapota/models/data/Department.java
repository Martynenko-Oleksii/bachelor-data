package ua.nure.liapota.models.data;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "departments")
public class Department {
    @Id
    @Column(name = "department_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "standart_department_id")
    private int standardDepartmentId;
    private String name;
    @Column(name = "short_name")
    private String shortName;
    @Column(name = "facility_id")
    private int facilityId;
    @Column(name = "created_by")
    private String createdBy;

    @JsonIgnore
    @OneToMany(mappedBy = "department")
    private Set<CostCenter> costCenters;

    public Set<CostCenter> getCostCenters() {
        return costCenters;
    }

    public void setCostCenters(Set<CostCenter> costCenters) {
        this.costCenters = costCenters;
    }

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

    public int getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(int facilityId) {
        this.facilityId = facilityId;
    }

    public int getStandardDepartmentId() {
        return standardDepartmentId;
    }

    public void setStandardDepartmentId(int standardDepartmentId) {
        this.standardDepartmentId = standardDepartmentId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
}
