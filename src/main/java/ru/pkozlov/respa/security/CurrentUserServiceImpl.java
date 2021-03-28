package ru.pkozlov.respa.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.security.Principal;

@Component
public class CurrentUserServiceImpl implements CurrentUserService {

    @Override
    public Mono<Authentication> getAuthentication() {
        return ReactiveSecurityContextHolder
                .getContext()
                .map(SecurityContext::getAuthentication);
    }

    @Override
    public Mono<String> getLogin() {
        return getAuthentication() != null ? getAuthentication().map(Principal::getName) : null;
    }
}
