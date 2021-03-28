package ru.pkozlov.respa.db.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.pkozlov.respa.db.entity.UserRole;

public interface UserRoleRepository extends ReactiveCrudRepository<UserRole, Long> {

    @Query("INSERT INTO respa.user_role (user_role_id, user_id, role_id) " +
            "SELECT nextval('respa.user_role_seq'), $1, unnest($2)")
    Flux<UserRole> insertUserRoles(Long userId, Long[] roleIds);

    Mono<Void> deleteAllByUserId(Long userId);
}
