package put.poznan.sport.entity;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class SportFacility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;
    private String address;
    private String type;
    private Integer membershipRequired;
    private String imageUrl;

    @OneToOne(mappedBy = "sportFacility", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private OpenHour openHour;

    @OneToMany(mappedBy = "sportFacility", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Coach> coaches;

    @OneToMany(mappedBy = "sportFacility", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TrainingSession> trainingSessions;

    @OneToMany(mappedBy = "sportFacility", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<EquipmentOwnership> equipmentOwnerships;

    @OneToMany(mappedBy = "sportFacility", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SportFacilityNews> sportFacilityNews;

}
