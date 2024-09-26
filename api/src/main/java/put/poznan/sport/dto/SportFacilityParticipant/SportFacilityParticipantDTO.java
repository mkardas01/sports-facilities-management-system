package put.poznan.sport.dto.SportFacilityParticipant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SportFacilityParticipantDTO {
    private Integer userId;
    private Integer sportFacilitiesId;
}
