package put.poznan.sport.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@IdClass(SportFacilityParticipantId.class)
@Data
public class SportFacilityParticipant {
    @Id
    private Integer userId;

    @Id
    private Integer sportFacilitiesId;

    private Integer isActive;

    @ManyToOne
    @JoinColumn(name = "userId", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "sportFacilitiesId", insertable = false, updatable = false)
    private SportFacility sportFacility;

}
