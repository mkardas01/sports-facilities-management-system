package put.poznan.sport.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
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
    private SportFacility sportFacility;

    @OneToMany(mappedBy = "trainingSession", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TrainingSessionParticipant> trainingSessionParticipants;

    // Getters and Setters
}
