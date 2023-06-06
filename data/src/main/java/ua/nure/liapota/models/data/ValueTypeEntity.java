package ua.nure.liapota.models.data;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "value_types")
public class ValueTypeEntity {
    @Id
    @Column(name = "value_type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @JsonIgnore
    @OneToMany(mappedBy = "valueType")
    private Set<GlRpMapping> mappings;

    public Set<GlRpMapping> getMappings() {
        return mappings;
    }

    public void setMappings(Set<GlRpMapping> mappings) {
        this.mappings = mappings;
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
}
