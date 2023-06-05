package ua.nure.liapota.models.data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "department_elements")
public class DepartmentElement {
    @Id
    @Column(name = "department_element_id")
    private int id;
    @Column(name = "standart_department_id")
    private int standardDepartmentId;
    private String name;
    @OneToMany(mappedBy = "departmentElement")
    private Set<GlRpMapping> mappings;

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

    public int getStandardDepartmentId() {
        return standardDepartmentId;
    }

    public void setStandardDepartmentId(int standardDepartmentId) {
        this.standardDepartmentId = standardDepartmentId;
    }

    public Set<GlRpMapping> getMappings() {
        return mappings;
    }

    public void setMappings(Set<GlRpMapping> mappings) {
        this.mappings = mappings;
    }
}
