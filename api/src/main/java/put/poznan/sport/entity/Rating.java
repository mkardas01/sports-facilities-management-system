package put.poznan.sport.entity;

import jakarta.persistence.*;

@Entity
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer objectId;
    private String type;
    private Integer rate;

    @ManyToOne
    @JoinColumn(name = "objectId", insertable = false, updatable = false)
    private SportFacility sportFacility;

    @ManyToOne
    @JoinColumn(name = "objectId", insertable = false, updatable = false)
    private TrainingSession trainingSession;

    @ManyToOne
    @JoinColumn(name = "objectId", insertable = false, updatable = false)
    private Coach coach;

    // Getters and Setters
}

