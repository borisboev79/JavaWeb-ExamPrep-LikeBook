package spring.exam.services.user;

import examprep.shoppinglist.domain.models.binding.UserLoginModel;
import examprep.shoppinglist.domain.models.binding.UserRegisterModel;

public interface AuthService {

    default void registerUser(UserRegisterModel userRegisterModel) {
    }

    default void loginUser(UserLoginModel userLoginModel) {
    }

    default void logoutUser() {
    }

    boolean isAuthentic(String username, String password);
}
