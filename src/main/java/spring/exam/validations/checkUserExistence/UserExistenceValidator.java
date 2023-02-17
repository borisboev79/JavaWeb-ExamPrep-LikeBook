package spring.exam.validations.checkUserExistence;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import spring.exam.domain.entities.User;
import spring.exam.domain.models.binding.UserLoginModel;
import spring.exam.services.user.UserService;

import java.util.Optional;

public class UserExistenceValidator implements ConstraintValidator<ValidateUserExistence, UserLoginModel> {

    private final UserService userService;
    private final PasswordEncoder encoder;

    @Autowired
    public UserExistenceValidator(UserService userService, PasswordEncoder encoder) {

        this.userService = userService;
        this.encoder = encoder;
    }


    @Override
    public void initialize(ValidateUserExistence constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UserLoginModel userLoginModel, ConstraintValidatorContext constraintValidatorContext) {
        Optional<User> optionalUser = userService.findByUsername(userLoginModel.getUsername());
        if (optionalUser.isEmpty()){
            return false;
        }
        String  rawPassword = userLoginModel.getPassword();
        String  encodedPassword  = optionalUser.get().getPassword();

        return encoder.matches(rawPassword, encodedPassword);

    }
}
