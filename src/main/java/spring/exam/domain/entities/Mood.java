package spring.exam.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import spring.exam.domain.enums.MoodType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="moods")
public class Mood extends BaseEntity{

    @Enumerated(EnumType.STRING)
    private MoodType name;

    @Column(columnDefinition = "TEXT")
    private String description;

}
