package put.poznan.sport.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@AllArgsConstructor
@Data
public class SportFacilityParticipantId implements Serializable {
    private Integer userId;
    private Integer sportFacilitiesId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SportFacilityParticipantId that = (SportFacilityParticipantId) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(sportFacilitiesId, that.sportFacilitiesId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, sportFacilitiesId);
    }

}
