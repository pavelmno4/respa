package ru.pkozlov.respa.service.security;

import reactor.core.publisher.Mono;
import ru.pkozlov.respa.dto.security.UserDto;

public interface UserService {

    Mono<UserDto> save(UserDto userDto);

    Mono<UserDto> find(Long userId);

    Mono<Void> delete(Long id);
}
