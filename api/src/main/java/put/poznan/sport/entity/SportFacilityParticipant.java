package put.poznan.sport.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@IdClass(SportFacilityParticipantId.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SportFacilityParticipant {
    @Id
    private Integer userId;

    @Id
    private Integer sportFacilitiesId;

    private Integer isActive;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id", insertable = false, updatable = false)
    private User user;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "sportFacilitiesId", referencedColumnName = "id", insertable = false, updatable = false)
    private SportFacility sportFacility;
}
