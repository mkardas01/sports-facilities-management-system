package put.poznan.sport.dto.Rating;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateRating {

    @NotNull(message = "Ocena nie może być pusta")
    @Min(value =  1, message = "Ocena musi być większa od 0")
    @Max(value =  5, message = "Ocena musi być mniejsza lub równa 5")
    private Integer rate;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Typ nie może być pusty")
    private ObjectType objectType;

    @NotNull(message = "Id nie może być puste")
    private Integer objectId;

}
