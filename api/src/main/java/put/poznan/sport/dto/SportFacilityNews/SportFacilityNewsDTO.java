package put.poznan.sport.dto.SportFacilityNews;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SportFacilityNewsDTO {

    private Integer sportFacilityNewsID;

    @NotBlank(message = "Typ nie może być pusty")
    private Integer sportFacilitiesId;

    @NotBlank(message = "Tytuł nie może być pusty")
    private String title;

    @NotBlank(message = "Opis nie może być pusty")
    private String description;

    @NotBlank(message = "Obraz nie może być pusty")
    private String imageUrl;
}
