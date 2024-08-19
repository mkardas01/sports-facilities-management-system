package put.poznan.sport.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CoachCreateResponse {
    private String name;
    private String surname;
    private Integer sportFacilitiesId;
    private String imageUrl;
}
