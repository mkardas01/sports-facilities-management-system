package put.poznan.sport.dto.TraningSession;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@AllArgsConstructor
@Data
public class TrainingSessionDTO {
    private Integer coachId;
    private Integer sportFacilityId;
    private String name;
    private String startHour;
    private Integer duration;
    private Integer isWeekly;
    private Date trainingDate;
    private Integer capacity;
}
