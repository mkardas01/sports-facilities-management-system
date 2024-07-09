package put.poznan.sport.entity;

import jakarta.persistence.*;

@Entity
public class SportFacilityNews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer sportFacilitiesId;
    private String title;
    private String description;
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "sportFacilitiesId", insertable = false, updatable = false)
    private SportFacility sportFacility;

    // Getters and Setters
}
