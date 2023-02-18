package spring.exam.services.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spring.exam.domain.entities.User;
import spring.exam.domain.helpers.LoggedUser;
import spring.exam.domain.models.binding.UserLoginModel;
import spring.exam.domain.models.binding.UserRegisterModel;
import spring.exam.repositories.UserRepository;

import java.util.ArrayList;

@Service
public class AuthServiceImpl implements AuthService{
    private final UserRepository userRepository;
    private final LoggedUser loggedUser;
    private final PasswordEncoder encoder;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, LoggedUser loggedUser, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
        this.encoder = encoder;
    }

    @Override
    public void registerUser(UserRegisterModel userRegisterModel) {
        this.userRepository.saveAndFlush(User.builder()
                .username(userRegisterModel.getUsername())
                .password(encoder.encode(userRegisterModel.getPassword()))
                .email(userRegisterModel.getEmail())
                .likedPosts(new ArrayList<>())
                .build());
    }

    @Override
    public void loginUser(UserLoginModel userLoginModel) {
        User user = this.userRepository.findByUsername(userLoginModel.getUsername()).orElse(new User());

        this.loggedUser.setId(user.getId());
        this.loggedUser.setUsername(user.getUsername());
    }

    @Override
    public void logoutUser() {
        this.loggedUser.clearUser();
    }

    @Override
    public boolean isAuthentic(String username, String password) {
        User user = this.userRepository.findByUsername(username).orElse(new User());
        String encodedPassword = user.getPassword();

            return encoder.matches(password, encodedPassword);
        }
    }

