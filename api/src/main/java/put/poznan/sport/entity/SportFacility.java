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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getMembershipRequired() {
        return membershipRequired;
    }

    public void setMembershipRequired(Integer membershipRequired) {
        this.membershipRequired = membershipRequired;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public OpenHour getOpenHour() {
        return openHour;
    }

    public void setOpenHour(OpenHour openHour) {
        this.openHour = openHour;
    }

    public List<Coach> getCoaches() {
        return coaches;
    }

    public void setCoaches(List<Coach> coaches) {
        this.coaches = coaches;
    }

    public List<TrainingSession> getTrainingSessions() {
        return trainingSessions;
    }

    public void setTrainingSessions(List<TrainingSession> trainingSessions) {
        this.trainingSessions = trainingSessions;
    }

    public List<EquipmentOwnership> getEquipmentOwnerships() {
        return equipmentOwnerships;
    }

    public void setEquipmentOwnerships(List<EquipmentOwnership> equipmentOwnerships) {
        this.equipmentOwnerships = equipmentOwnerships;
    }

    public List<SportFacilityNews> getSportFacilityNews() {
        return sportFacilityNews;
    }

    public void setSportFacilityNews(List<SportFacilityNews> sportFacilityNews) {
        this.sportFacilityNews = sportFacilityNews;
    }
}
