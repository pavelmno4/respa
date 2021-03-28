package ru.pkozlov.respa.web.validator;

import org.springframework.util.StringUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.pkozlov.respa.exception.RequestValidationException;

public abstract class RequestValidator<T> implements Validator {

    public void validateRequest(T requestDto) {
        if (requestDto == null) {
            throw new RequestValidationException("exception.request.null");
        }

        Errors errors = new BeanPropertyBindingResult(requestDto, requestDto.getClass().getSimpleName());
        this.validate(requestDto, errors);

        if (errors.getFieldErrorCount() != 0) {
            String[] details = errors.getFieldErrors().stream()
                    .map(fe -> fe.getField() + ": " + fe.getDefaultMessage())
                    .toArray(String[]::new);
            throw new RequestValidationException("exception.request.default", details);
        }

        if (errors.getGlobalError() != null) {
            String defaultMessage = errors.getGlobalError().getDefaultMessage();
            String message = StringUtils.hasText(defaultMessage) ? defaultMessage : "exception.request.default";
            throw new RequestValidationException(message);
        }
    }
}
