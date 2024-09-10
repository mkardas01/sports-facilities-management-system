package put.poznan.sport.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private SportFacility sportFacility;

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSportFacilitiesId() {
        return sportFacilitiesId;
    }

    public void setSportFacilitiesId(Integer sportFacilitiesId) {
        this.sportFacilitiesId = sportFacilitiesId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public SportFacility getSportFacility() {
        return sportFacility;
    }

    public void setSportFacility(SportFacility sportFacility) {
        this.sportFacility = sportFacility;
    }
}
