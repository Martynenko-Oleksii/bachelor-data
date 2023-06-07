package ua.nure.liapota.models.warehouse;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "measures")
public class Measure {
    @Id
    @Column(name = "measure_id")
    private int id;
    @Column(name = "department_element_name")
    private String departmentElementName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartmentElementName() {
        return departmentElementName;
    }

    public void setDepartmentElementName(String departmentElementName) {
        this.departmentElementName = departmentElementName;
    }
}
