package put.poznan.sport.entity;

import jakarta.persistence.*;

@Entity
@IdClass(SportFacilityParticipantId.class)
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
