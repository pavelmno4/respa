package ru.pkozlov.respa.web.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import ru.pkozlov.respa.dto.security.UserDto;

@Component
public class UserRequestValidator extends RequestValidator<UserDto> {
    @Override
    public boolean supports(Class<?> clazz) {
        return UserDto.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        UserDto userDto = (UserDto) obj;

        if (userDto.getLogin() == null || userDto.getLogin().isBlank()) {
            errors.rejectValue("login", "field.required", "must not be blank");
        }
    }
}
