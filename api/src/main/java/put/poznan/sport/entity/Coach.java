package put.poznan.sport.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Coach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private Integer sportFacilitiesId;

    @Column(nullable = false)
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "sportFacilitiesId", insertable = false, updatable = false)
    private SportFacility sportFacility;

    @OneToMany(mappedBy = "coach", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TrainingSession> trainingSessions;

}
