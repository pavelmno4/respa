package ru.pkozlov.respa.db.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import ru.pkozlov.respa.db.entity.Role;

import java.util.List;

public interface RoleRepository extends ReactiveCrudRepository<Role, Long> {

    @Query("SELECT r.role_id, r.role_name, r.description, r.create_date " +
            "FROM respa.user_role ur" +
            "   JOIN respa.role AS r ON (ur.role_id = r.role_id)" +
            "   JOIN respa.usr AS u ON (ur.user_id = u.user_id)" +
            "WHERE u.user_id = $1")
    Flux<Role> findAllByUserId(Long userId);

    @Query("SELECT r.role_id, r.role_name, r.description, r.create_date " +
            "            FROM respa.user_role ur " +
            "                    JOIN respa.role r ON (ur.role_id = r.role_id) " +
            "                    JOIN respa.usr u ON (ur.user_id = u.user_id) " +
            "            WHERE u.login = $1")
    Flux<Role> findAllByUserLogin(String login);

    Flux<Role> findAllByRoleNameIn(List<String> roleNames);
}
