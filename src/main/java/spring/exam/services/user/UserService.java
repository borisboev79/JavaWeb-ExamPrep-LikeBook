package spring.exam.services.user;


import examprep.shoppinglist.domain.entities.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByUsername(String username);
}
