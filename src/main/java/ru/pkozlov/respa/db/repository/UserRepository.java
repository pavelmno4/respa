package ru.pkozlov.respa.db.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;
import ru.pkozlov.respa.db.entity.User;

public interface UserRepository extends ReactiveCrudRepository<User, Long> {

    Mono<User> findByLogin(String login);
}
