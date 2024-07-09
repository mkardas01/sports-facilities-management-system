package put.poznan.sport.entity;

import jakarta.persistence.*;

@Entity
public class OpenHour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sportFacilityId;

    private String monday;
    private String tuesday;
    private String wednesday;
    private String thursday;
    private String friday;
    private String saturday;
    private String sunday;

    @OneToOne
    @MapsId
    @JoinColumn(name = "sportFacilityId")
    private SportFacility sportFacility;
}

