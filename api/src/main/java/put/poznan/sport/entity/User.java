package put.poznan.sport.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String surname;
    private String email;
    private String imageUrl;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TrainingSessionParticipant> trainingSessionParticipants;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SportFacilityParticipant> sportFacilityParticipants;

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<TrainingSessionParticipant> getTrainingSessionParticipants() {
        return trainingSessionParticipants;
    }

    public void setTrainingSessionParticipants(List<TrainingSessionParticipant> trainingSessionParticipants) {
        this.trainingSessionParticipants = trainingSessionParticipants;
    }

    public List<SportFacilityParticipant> getSportFacilityParticipants() {
        return sportFacilityParticipants;
    }

    public void setSportFacilityParticipants(List<SportFacilityParticipant> sportFacilityParticipants) {
        this.sportFacilityParticipants = sportFacilityParticipants;
    }
}
