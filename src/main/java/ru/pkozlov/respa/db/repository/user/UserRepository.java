package ru.pkozlov.respa.db.repository.user;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;
import ru.pkozlov.respa.db.entity.User;

public interface UserRepository extends ReactiveCrudRepository<User, Long>, CustomUserRepository {

    Mono<User> findByLogin(String login);
}
