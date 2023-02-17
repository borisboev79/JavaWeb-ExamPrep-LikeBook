package spring.exam.services.user;

import spring.exam.domain.models.binding.UserLoginModel;
import spring.exam.domain.models.binding.UserRegisterModel;

public interface AuthService {

    default void registerUser(UserRegisterModel userRegisterModel) {
    }

    default void loginUser(UserLoginModel userLoginModel) {
    }

    default void logoutUser() {
    }

    boolean isAuthentic(String username, String password);
}
