package ru.pkozlov.respa.db.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import ru.pkozlov.respa.db.entity.ParticipantTeam;

public interface ParticipantTeamRepository extends ReactiveCrudRepository<ParticipantTeam, Long> {
}
