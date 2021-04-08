package ru.pkozlov.respa.service.security;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import ru.pkozlov.respa.db.entity.Role;
import ru.pkozlov.respa.db.entity.User;
import ru.pkozlov.respa.db.repository.RoleRepository;
import ru.pkozlov.respa.db.repository.UserRepository;
import ru.pkozlov.respa.db.repository.UserRoleRepository;
import ru.pkozlov.respa.dto.security.UserDto;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final UserRoleRepository userRoleRepo;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Override
    public Mono<UserDto> save(UserDto userDto) {

        User userNew = User.builder()
                .id(userDto.getUserId())
                .login(userDto.getLogin())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .status(userDto.getStatus())
                .lastUpdateDate(LocalDateTime.now())
                .build();

        return Mono.zip(
                userRepo.save(userNew),
                roleRepo.findAllByRoleNameIn(userDto.getRoleNameList()).collectList(),
                (savedUser, roles) -> {
                    Long userId = savedUser.getId();

                    Long[] roleIds = roles.stream()
                            .map(Role::getId)
                            .toArray(Long[]::new);

                    userRoleRepo.deleteAllByUserId(userId).subscribe();
                    userRoleRepo.insertUserRoles(userId, roleIds).subscribe();

                    savedUser.setUserRoles(roles);

                    return savedUser;
                })
                .map(user -> modelMapper.map(user, UserDto.class));
    }

    @Override
    public Mono<UserDto> find(Long userId) {
        return Mono.zip(
                userRepo.findById(userId),
                roleRepo.findAllByUserId(userId).collectList(),
                (user, roles) -> {
                    user.setUserRoles(roles);

                    return user;
                }
        ).map(user -> modelMapper.map(user, UserDto.class));
    }

    @Override
    public Mono<Void> delete(Long id) {
        return userRepo.deleteById(id)
                .doOnSuccess(u -> userRoleRepo.deleteAllByUserId(id).subscribe());
    }
}
