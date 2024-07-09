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
}
