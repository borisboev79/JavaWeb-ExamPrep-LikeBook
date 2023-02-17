package spring.exam.domain.entities;

import examprep.shoppinglist.domain.enums.CategoryType;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="categories")
public class Category extends BaseEntity{

    @Enumerated(EnumType.STRING)
    private CategoryType name;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

}
