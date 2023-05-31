package ua.nure.liapota.models.data;

import javax.persistence.*;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @Column(name = "code")
    private String code;
    private String description;
    private String source;
    private char type;
    @Column(name = "facility_id")
    private int facilityId;
    @Column(name = "added_by")
    private String addedBy;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public int getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(int facilityId) {
        this.facilityId = facilityId;
    }

    public String getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
