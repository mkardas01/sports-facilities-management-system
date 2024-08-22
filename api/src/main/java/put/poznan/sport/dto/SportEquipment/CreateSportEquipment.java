package put.poznan.sport.dto.SportEquipment;

import jakarta.persistence.Column;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CreateSportEquipment {

    @NotBlank(message = "Typ nie może być pusty")
    private String type;

    @NotBlank(message = "Marka nie może być pusty")
    private String brand;

    @NotBlank(message = "Model nie może być pusty")
    private String model;

    @NotBlank(message = "Opis nie może być pusty")
    private String description;

    @NotBlank(message = "Link do zdjęcia nie może być pusty")
    private String imageUrl;

    @NotNull(message = "Obiekt sportowy nie może być pusty")
    private Integer sportFacilityId;
}
