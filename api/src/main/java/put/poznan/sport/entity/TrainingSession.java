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
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCoachesId() {
        return coachesId;
    }

    public void setCoachesId(Integer coachesId) {
        this.coachesId = coachesId;
    }

    public Integer getSportFacilitiesId() {
        return sportFacilitiesId;
    }

    public void setSportFacilitiesId(Integer sportFacilitiesId) {
        this.sportFacilitiesId = sportFacilitiesId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartHour() {
        return startHour;
    }

    public void setStartHour(String startHour) {
        this.startHour = startHour;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getIsWeekly() {
        return isWeekly;
    }

    public void setIsWeekly(Integer isWeekly) {
        this.isWeekly = isWeekly;
    }

    public Date getTrainingDate() {
        return trainingDate;
    }

    public void setTrainingDate(Date trainingDate) {
        this.trainingDate = trainingDate;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getFreeBooked() {
        return freeBooked;
    }

    public void setFreeBooked(Integer freeBooked) {
        this.freeBooked = freeBooked;
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public SportFacility getSportFacility() {
        return sportFacility;
    }

    public void setSportFacility(SportFacility sportFacility) {
        this.sportFacility = sportFacility;
    }

    public List<TrainingSessionParticipant> getTrainingSessionParticipants() {
        return trainingSessionParticipants;
    }

    public void setTrainingSessionParticipants(List<TrainingSessionParticipant> trainingSessionParticipants) {
        this.trainingSessionParticipants = trainingSessionParticipants;
    }
}
