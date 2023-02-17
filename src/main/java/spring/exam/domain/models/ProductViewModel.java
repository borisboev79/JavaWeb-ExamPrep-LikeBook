package spring.exam.domain.models;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductViewModel {
    private Long id;
    private String name;
    private BigDecimal price;

    @Override
    public String toString() {
        return String.format("Name: %s Price: %.2f lv", name, price);
    }
}
