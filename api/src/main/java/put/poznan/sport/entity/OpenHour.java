package put.poznan.sport.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OpenHour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String monday;
    
    private String tuesday;
    
    private String wednesday;
    
    private String thursday;
    
    private String friday;
    
    private String saturday;
    
    private String sunday;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private SportFacility sportFacility;

}

