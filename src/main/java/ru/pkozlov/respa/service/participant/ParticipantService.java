package ru.pkozlov.respa.service.participant;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.pkozlov.respa.dto.participant.ParticipantCardDto;

public interface ParticipantService {
    Mono<ParticipantCardDto> save(ParticipantCardDto participantDto);

    Mono<ParticipantCardDto> find(Long participantId);

    Flux<ParticipantCardDto> findAllInCategory(Integer age, Integer weightCategory);

    Mono<Void> delete(Long participantId);
}