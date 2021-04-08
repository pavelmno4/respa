package ru.pkozlov.respa.db.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;
import ru.pkozlov.respa.db.entity.Team;

public interface TeamRepository extends ReactiveCrudRepository<Team, Long> {

    Mono<Team> findByTeamName(String teamName);
}
