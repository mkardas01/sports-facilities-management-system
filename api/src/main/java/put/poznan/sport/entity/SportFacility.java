package put.poznan.sport.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import put.poznan.sport.entity.openHour.OpenHour;

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
    private boolean membershipRequired;
    private String imageUrl;

    @ManyToMany(mappedBy = "managedFacilities")
    private List<User> managers;

    @OneToOne(mappedBy = "sportFacility", orphanRemoval = true)
    private OpenHour openHour;

    @OneToMany(mappedBy = "sportFacility", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Coach> coaches;

    @OneToMany(mappedBy = "sportFacility", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TrainingSession> trainingSessions;

    @OneToMany(mappedBy = "sportFacility", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SportFacilityNews> sportFacilityNews;

    @OneToMany(mappedBy = "ownerSportFacility", orphanRemoval = true)
    private List<SportEquipment> sportEquipments;

}
