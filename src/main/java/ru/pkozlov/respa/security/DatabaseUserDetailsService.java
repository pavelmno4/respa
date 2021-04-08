package ru.pkozlov.respa.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import ru.pkozlov.respa.db.repository.RoleRepository;
import ru.pkozlov.respa.db.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class DatabaseUserDetailsService implements ReactiveUserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public Mono<UserDetails> findByUsername(String login) {
        return userRepository.findByLogin(login)
                .switchIfEmpty(Mono.error(new UsernameNotFoundException("User not found: " + login)))
                .flatMap(usr -> roleRepository.findAllByUserLogin(login)
                        .map(role -> new SimpleGrantedAuthority(role.getRoleName().toUpperCase()))
                        .switchIfEmpty(Mono.error(new UsernameNotFoundException("User with no roles found: " + login)))
                        .cast(GrantedAuthority.class)
                        .collectList()
                        .map(rls -> User.builder()
                                .username(usr.getLogin())
                                .password(usr.getPassword())
                                .authorities(rls)
                                .build()
                        )
                );
    }
}
