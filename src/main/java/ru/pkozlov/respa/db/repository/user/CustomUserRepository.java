package ru.pkozlov.respa.db.repository.user;

import reactor.core.publisher.Mono;
import ru.pkozlov.respa.db.entity.User;

public interface CustomUserRepository {

    Mono<User> findUserById(Long userId);

    Mono<User> saveUserWithRoles(User user);
}
