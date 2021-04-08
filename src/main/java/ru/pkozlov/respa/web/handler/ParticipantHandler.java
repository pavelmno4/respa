package ru.pkozlov.respa.web.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import ru.pkozlov.respa.db.entity.Participant;
import ru.pkozlov.respa.dto.participant.ParticipantCardDto;
import ru.pkozlov.respa.service.participant.ParticipantService;
import ru.pkozlov.respa.web.validator.ParticipantRequestValidator;

@Component
@RequiredArgsConstructor
public class ParticipantHandler {
    private final ParticipantService participantService;
    private final ParticipantRequestValidator participantRequestValidator;

    public Mono<ServerResponse> save(ServerRequest request) {
        return request
                .bodyToMono(ParticipantCardDto.class)
                .doOnSuccess(participantRequestValidator::validateRequest)
                .flatMap(participantRequest -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(
                                participantService.save(participantRequest),
                                ParticipantCardDto.class
                        )
                );
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public Mono<ServerResponse> findById(ServerRequest request) {
        Long participantId = Long.parseLong(request.pathVariable("id"));

        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        participantService.find(participantId),
                        ParticipantCardDto.class
                );
    }

    public Mono<ServerResponse> findAllInCategory(ServerRequest request) {
        Long age = Long.parseLong(request.pathVariable("age"));
        Long weight = Long.parseLong(request.pathVariable("weight"));

        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        participantService.findAllInCategory(age, weight),
                        Participant.class
                );
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public Mono<ServerResponse> deleteById(ServerRequest request) {
        Long participantId = Long.parseLong(request.pathVariable("id"));

        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        participantService.delete(participantId),
                        Void.class
                );
    }


}
