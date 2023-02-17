package spring.exam.domain.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostViewModel {
    private Long id;
    private String content;
    private Integer userLikes;


}
