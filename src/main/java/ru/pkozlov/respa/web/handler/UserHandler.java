package ru.pkozlov.respa.web.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import ru.pkozlov.respa.dto.security.UserDto;
import ru.pkozlov.respa.service.security.UserService;
import ru.pkozlov.respa.web.validator.UserRequestValidator;

@Component
@RequiredArgsConstructor
public class UserHandler {
    private final UserService userService;
    private final UserRequestValidator userRequestValidator;

    @PreAuthorize("hasAuthority('ADMIN')")
    public Mono<ServerResponse> save(ServerRequest request) {
        return request
                .bodyToMono(UserDto.class)
                .doOnSuccess(userRequestValidator::validateRequest)
                .flatMap(userRequest -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(
                                userService.save(userRequest),
                                UserDto.class
                        )
                );
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public Mono<ServerResponse> findById(ServerRequest request) {
        Long userId = Long.parseLong(request.pathVariable("id"));

        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        userService.find(userId),
                        UserDto.class
                );
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public Mono<ServerResponse> deleteById(ServerRequest request) {
        Long userId = Long.parseLong(request.pathVariable("id"));

        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        userService.delete(userId),
                        Void.class
                );
    }
}
