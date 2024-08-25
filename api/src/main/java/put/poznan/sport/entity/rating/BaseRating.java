package put.poznan.sport.entity.rating;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import put.poznan.sport.entity.User;

@Getter
@MappedSuperclass
public abstract class BaseRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer rate;

    @ManyToOne
    @JoinColumn(name = "addedBy")
    private User user;
}
