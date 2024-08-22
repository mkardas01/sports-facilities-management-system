package put.poznan.sport.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@IdClass(EquipmentOwnershipId.class)
@Builder
@AllArgsConstructor
@NoArgsConstructor
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