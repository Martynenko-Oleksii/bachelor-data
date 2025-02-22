package ua.nure.liapota.models.security;

import javax.persistence.*;

@Entity
@Table(name = "users_customers")
public class UserCustomer {
    @Id
    @Column(name = "user_id")
    private String userId;
    @Column(name = "customer_id")
    private String customerId;

    @ManyToOne
    @JoinColumn(name = "facility_group_id")
    private FacilityGroup facilityGroup;

    public FacilityGroup getFacilityGroup() {
        return facilityGroup;
    }

    public void setFacilityGroup(FacilityGroup facilityGroup) {
        this.facilityGroup = facilityGroup;
    }

    public String getUserId() {
        return userId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerId() {
        return customerId;
    }
}
