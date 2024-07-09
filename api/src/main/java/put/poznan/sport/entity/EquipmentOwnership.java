package put.poznan.sport.entity;

import jakarta.persistence.*;

@Entity
@IdClass(EquipmentOwnershipId.class)
public class EquipmentOwnership {
    @Id
    private Integer sportEquipmentId;

    @Id
    private Integer sportFacilitiesId;

    private Integer amount;

    @ManyToOne
    @JoinColumn(name = "sportFacilitiesId", insertable = false, updatable = false)
    private SportFacility sportFacility;

    @ManyToOne
    @JoinColumn(name = "sportEquipmentId", insertable = false, updatable = false)
    private SportEquipment sportEquipment;

}