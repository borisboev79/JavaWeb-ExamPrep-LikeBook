package spring.exam.validations.passwordMatcher;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import spring.exam.domain.models.binding.UserRegisterModel;

public class PasswordMatcher implements ConstraintValidator<PasswordMatch, UserRegisterModel> {
    @Override
    public void initialize(PasswordMatch constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UserRegisterModel userRegisterModel, ConstraintValidatorContext context) {

        return userRegisterModel.getPassword() != null && userRegisterModel.getPassword().equals(userRegisterModel.getConfirmPassword());

    }
}
