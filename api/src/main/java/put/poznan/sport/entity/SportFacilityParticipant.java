package put.poznan.sport.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@IdClass(SportFacilityParticipantId.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SportFacilityParticipant {
    @Id
    private Integer userId;

    @Id
    private Integer sportFacilitiesId;

    private Integer isActive;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "sportFacilitiesId", referencedColumnName = "id", insertable = false, updatable = false)
    private SportFacility sportFacility;
}
