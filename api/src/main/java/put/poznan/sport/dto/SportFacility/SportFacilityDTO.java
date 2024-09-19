package put.poznan.sport.dto.SportFacility;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SportFacilityDTO {
    private String name;
    private String description;
    private String address;
    private String type;
    private boolean membershipRequired;
    private String imageUrl;
}
