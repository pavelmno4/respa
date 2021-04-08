package ru.pkozlov.respa.web.validator;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import ru.pkozlov.respa.dto.category.CategoryDto;

import java.util.List;
import java.util.regex.Pattern;

@Component
public class CategoryRequestValidator extends RequestValidator<CategoryDto> {
    @Override
    public boolean supports(Class<?> clazz) {
        return CategoryDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CategoryDto categoryDto = (CategoryDto) target;

        checkAges(categoryDto.getAges(), errors);
        checkWeights(categoryDto.getWeights(), errors);

    }

    private void checkAges(String ages, Errors errors) {
        if (!StringUtils.hasLength(ages) ||
                Pattern.compile("[^\\d-]").matcher(ages).find()) {
            errors.reject("field.required.group", "error.age.range.format");
        }
    }

    private void checkWeights(List<Integer> weights, Errors errors) {
        if (weights.stream().anyMatch(w -> (w <= 0))) {
            errors.reject("field.required.group", "error.weigh.value");
        }
    }
}
