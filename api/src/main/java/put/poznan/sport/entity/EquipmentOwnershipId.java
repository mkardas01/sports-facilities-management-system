package put.poznan.sport.entity;

import java.io.Serializable;
import java.util.Objects;

public class EquipmentOwnershipId implements Serializable {
    private Integer sportEquipmentId;
    private Integer sportFacilitiesId;

    // Default constructor

    public EquipmentOwnershipId() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EquipmentOwnershipId that = (EquipmentOwnershipId) o;
        return Objects.equals(sportEquipmentId, that.sportEquipmentId) &&
                Objects.equals(sportFacilitiesId, that.sportFacilitiesId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sportEquipmentId, sportFacilitiesId);
    }
}
