package ru.pkozlov.respa.dto.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    List<String> roleNameList;
    private Long userId;
    private String login;
    private String password;
    private Boolean status;
}
