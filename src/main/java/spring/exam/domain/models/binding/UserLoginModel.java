package spring.exam.domain.models.binding;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spring.exam.validations.checkUserExistence.ValidateUserExistence;

@Getter
@Setter
@NoArgsConstructor
@ValidateUserExistence
public class UserLoginModel {

    @Size(min = 3, max = 20)
    @NotNull
    private String username;

    @Size(min = 3, max = 20)
    @NotNull
    private String password;


}
