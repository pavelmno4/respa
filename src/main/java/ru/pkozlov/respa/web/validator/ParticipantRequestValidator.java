package ru.pkozlov.respa.web.validator;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import ru.pkozlov.respa.dto.participant.ParticipantCardDto;

import java.util.regex.Pattern;

@Component
public class ParticipantRequestValidator extends RequestValidator<ParticipantCardDto> {

    @Override
    public boolean supports(Class<?> clazz) {
        return ParticipantCardDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ParticipantCardDto participantCardDto = (ParticipantCardDto) target;

        checkNameField(participantCardDto.getFirstName(), errors);
        checkNameField(participantCardDto.getLastName(), errors);
        checkNumberFormat(participantCardDto.getBirthYear(), errors);
        checkSexField(participantCardDto.getGender(), errors);
        checkNumberFormat(participantCardDto.getWeight(), errors);
        checkNameField(participantCardDto.getTeam().getTeamName(), errors);
        checkNameField(participantCardDto.getTeam().getCity(), errors);
    }

    private void checkNameField(String field, Errors errors) {
        if (!StringUtils.hasLength(field) ||
                Pattern.compile("[^A-Za-zА-Яа-яё\\s]").matcher(field).find()) {
            errors.reject("field.required.group", "error.text.field.format");
        }
    }

    private void checkSexField(String sexField, Errors errors) {
        if (!StringUtils.hasLength(sexField) ||
                Pattern.compile("[^МЖ]").matcher(sexField).find()) {
            errors.reject("field.required.group", "error.sex.field.format");
        }
    }

    private void checkNumberFormat(String field, Errors errors) {
        if (!StringUtils.hasLength(field) ||
                Pattern.compile("[^\\d.]").matcher(field).find()) {
            errors.reject("field.required.group", "error.number.field.format");
        }
    }
}
