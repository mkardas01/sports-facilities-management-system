package put.poznan.sport.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
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

    @ManyToMany(mappedBy = "managedFacilities")
    private List<User> managers;

    @OneToOne(mappedBy = "sportFacility", cascade = CascadeType.ALL)
    private OpenHour openHour;

    @OneToMany(mappedBy = "sportFacility", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Coach> coaches;

    @OneToMany(mappedBy = "sportFacility", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TrainingSession> trainingSessions;

    @OneToMany(mappedBy = "sportFacility", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SportFacilityNews> sportFacilityNews;

    @OneToMany(mappedBy = "ownerSportFacility")
    private List<SportEquipment> sportEquipments;

}
