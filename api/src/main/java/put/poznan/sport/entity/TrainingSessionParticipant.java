package put.poznan.sport.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@IdClass(TrainingSessionParticipantId.class)
@Data
public class TrainingSessionParticipant {
    @Id
    private Integer userId;

    @Id
    private Integer trainingSessionId;

    @ManyToOne
    @JoinColumn(name = "userId", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "trainingSessionId", insertable = false, updatable = false)
    private TrainingSession trainingSession;

    // Getters and Setters
}
