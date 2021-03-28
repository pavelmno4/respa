package ru.pkozlov.respa.security;

import org.springframework.security.core.Authentication;
import reactor.core.publisher.Mono;

public interface CurrentUserService {

    Mono<Authentication> getAuthentication();

    Mono<String> getLogin();
}
