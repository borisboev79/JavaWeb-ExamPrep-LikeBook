package spring.exam.domain.models;

import examprep.shoppinglist.domain.enums.CategoryType;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductAddModel {

    @Size(min = 3, max = 20)
    @NotEmpty
    private String name;

    @Size(min = 5)
    @NotNull
    private String description;

    @Positive
    @NotNull
    private BigDecimal price;

    @NotNull
    @FutureOrPresent
    private LocalDateTime neededBefore;

    @NotNull
    private CategoryType category;
}
