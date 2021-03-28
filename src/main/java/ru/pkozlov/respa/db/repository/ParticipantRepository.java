package ru.pkozlov.respa.db.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import ru.pkozlov.respa.db.entity.Participant;

public interface ParticipantRepository extends ReactiveCrudRepository<Participant, Long> {
}
