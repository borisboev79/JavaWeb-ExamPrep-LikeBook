package spring.exam.domain.models;

import lombok.*;
import spring.exam.domain.entities.Mood;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostViewModel {
    private Long id;
    private String username;
    private Mood mood;
    private String content;
    private Integer userLikes;


}
