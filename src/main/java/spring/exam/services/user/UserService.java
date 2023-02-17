package spring.exam.services.user;


import spring.exam.domain.entities.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByUsername(String username);
}
