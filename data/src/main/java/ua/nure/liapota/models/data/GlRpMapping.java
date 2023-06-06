package ua.nure.liapota.models.data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "GL_RP_mappings")
public class GlRpMapping {
    @Id
    @Column(name = "GL_PR_mapping_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String description;
    @ManyToOne
    @JoinColumn(name = "CC_number")
    private CostCenter costCenter;
    @ManyToOne
    @JoinColumn(name = "account_code")
    private Account account;
    @ManyToOne
    @JoinColumn(name = "department_element_id")
    private DepartmentElement departmentElement;
    @ManyToOne
    @JoinColumn(name = "valur_type_id")
    private ValueTypeEntity valueType;
    @OneToMany(mappedBy = "mapping", fetch = FetchType.EAGER)
    private Set<Value> values;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public CostCenter getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(CostCenter costCenter) {
        this.costCenter = costCenter;
    }

    public DepartmentElement getDepartmentElement() {
        return departmentElement;
    }

    public void setDepartmentElement(DepartmentElement departmentElement) {
        this.departmentElement = departmentElement;
    }

    public ValueTypeEntity getValueType() {
        return valueType;
    }

    public void setValueType(ValueTypeEntity valueType) {
        this.valueType = valueType;
    }

    public Set<Value> getValues() {
        return values;
    }

    public void setValues(Set<Value> values) {
        this.values = values;
    }
}
