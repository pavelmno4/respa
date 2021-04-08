package ru.pkozlov.respa.db.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import ru.pkozlov.respa.db.entity.Participant;

public interface ParticipantRepository extends ReactiveCrudRepository<Participant, Long> {

    @Query("select * from respa.participant " +
            "where birth_year = :age and weight = :weight")
    Flux<Participant> findAllInCategory(Long age, Long weight);
}
