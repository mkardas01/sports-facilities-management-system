package put.poznan.sport.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class TrainingSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer coachesId;
    private Integer sportFacilitiesId;
    private String name;
    private String startHour;
    private Integer duration;
    private Integer isWeekly;
    private Date trainingDate;
    private Integer capacity;
    private Integer freeBooked;

    @ManyToOne
    @JoinColumn(name = "coachesId", insertable = false, updatable = false)
    private Coach coach;

    @ManyToOne
    @JoinColumn(name = "sportFacilitiesId", insertable = false, updatable = false)
    @JsonIgnore
    private SportFacility sportFacility;

    @OneToMany(mappedBy = "trainingSession", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TrainingSessionParticipant> trainingSessionParticipants;
}
