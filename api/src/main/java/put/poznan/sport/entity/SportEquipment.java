package put.poznan.sport.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class SportEquipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String type;
    private String brand;
    private String model;
    private String description;
    private String imageUrl;

    @OneToMany(mappedBy = "sportEquipment", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<EquipmentOwnership> equipmentOwnerships;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
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

    public List<EquipmentOwnership> getEquipmentOwnerships() {
        return equipmentOwnerships;
    }

    public void setEquipmentOwnerships(List<EquipmentOwnership> equipmentOwnerships) {
        this.equipmentOwnerships = equipmentOwnerships;
    }

}
