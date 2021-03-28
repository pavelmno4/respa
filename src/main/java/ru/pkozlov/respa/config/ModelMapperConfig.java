package ru.pkozlov.respa.config;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.pkozlov.respa.db.entity.User;
import ru.pkozlov.respa.dto.security.UserDto;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setSkipNullEnabled(true);
        mapper.addConverter(userToUserDto());

        return mapper;
    }

    private Converter<User, UserDto> userToUserDto() {
        return new Converter<User, UserDto>() {
            @Override
            public UserDto convert(MappingContext<User, UserDto> context) {
                User source = context.getSource();

                if (source == null) {
                    return null;
                }

                List<String> userRolesNames = source.getUserRoles().stream()
                        .map(r -> r.getRoleName())
                        .collect(Collectors.toList());

                return UserDto.builder()
                        .userId(source.getId())
                        .login(source.getLogin())
                        .password("<<HIDDEN>>") // Showing hashed or pure password is not secure
                        .status(source.getStatus())
                        .roleNameList(userRolesNames)
                        .build();
            }
        };
    }
}
