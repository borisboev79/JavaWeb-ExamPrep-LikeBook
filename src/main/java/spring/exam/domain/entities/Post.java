package spring.exam.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "posts")
public class Post extends BaseEntity {

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @Fetch(FetchMode.JOIN)
    private Mood mood;

    @ManyToOne
    @Fetch(FetchMode.JOIN)
    private User user;

    @ManyToMany
    @Fetch(FetchMode.JOIN)
    private List<User> userLikes;
}
