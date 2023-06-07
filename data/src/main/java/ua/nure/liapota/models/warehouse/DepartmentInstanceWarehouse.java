package ua.nure.liapota.models.warehouse;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "department_instance")
public class DepartmentInstanceWarehouse {
    @Id
    @Column(name = "department_instance_id")
    private int id;
    @Column(name = "standart_department_name")
    private String standardDepartmentName;
    private String name;
    @Column(name = "short_name")
    private String shortName;
    @Column(name = "CC_name")
    private String costCenterName;
    @Column(name = "standart_department_id")
    private int standardDepartmentId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public int getStandardDepartmentId() {
        return standardDepartmentId;
    }

    public void setStandardDepartmentId(int standardDepartmentId) {
        this.standardDepartmentId = standardDepartmentId;
    }

    public String getCostCenterName() {
        return costCenterName;
    }

    public void setCostCenterName(String costCenterName) {
        this.costCenterName = costCenterName;
    }

    public String getStandardDepartmentName() {
        return standardDepartmentName;
    }

    public void setStandardDepartmentName(String standardDepartmentName) {
        this.standardDepartmentName = standardDepartmentName;
    }
}
