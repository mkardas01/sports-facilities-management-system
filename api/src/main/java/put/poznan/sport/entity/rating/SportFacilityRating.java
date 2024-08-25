package put.poznan.sport.entity.rating;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import put.poznan.sport.entity.SportFacility;

@Getter
@Setter
@Entity
public class SportFacilityRating extends BaseRating {

    @ManyToOne
    @JoinColumn(name = "objectId")
    private SportFacility sportFacility;
}
