package ru.pkozlov.respa.db.repository.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import ru.pkozlov.respa.db.entity.User;
import ru.pkozlov.respa.db.repository.RoleRepository;

@Repository
@RequiredArgsConstructor
public class CustomUserRepositoryImpl implements CustomUserRepository {
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final RoleRepository roleRepo;

    @Override
    public Mono<User> findUserById(Long userId) {
        return null;
    }

    @Override
    public Mono<User> saveUserWithRoles(User user) {
        return null;
    }
}
