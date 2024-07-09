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

}
