package put.poznan.sport.dto.Rating;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import put.poznan.sport.entity.User;

@EqualsAndHashCode(callSuper = true)
@Data
public class Rating extends CreateRating{
    private Integer id;

    @JsonIgnore
    private User addedBy;
}
