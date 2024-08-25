package put.poznan.sport.entity.rating;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import put.poznan.sport.entity.TrainingSession;

@Getter
@Setter
@Entity
public class TrainingSessionRating extends BaseRating {

    @ManyToOne
    @JoinColumn(name = "objectId")
    private TrainingSession trainingSession;
}
