package spring.exam.domain.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;
import spring.exam.domain.enums.MoodType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostAddModel {

    @Size(min = 2, max = 150)
    @NotEmpty
    private String content;

    @PositiveOrZero
    @NotNull
    private Integer userLikes;

    @NotNull
    private MoodType mood;
}
