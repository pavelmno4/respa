package ru.pkozlov.respa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class ResourceBundleMessageServiceImpl implements ResourceBundleMessageService {
    private static final Locale ru_RU = new Locale("ru", "RU");
    private final ResourceBundleMessageSource messageSource;

    @Override
    public String getMessage(String code) {
        return messageSource.getMessage(code, null, ru_RU);
    }

    @Override
    public String getMessage(String code, Object... args) {
        return messageSource.getMessage(code, args, ru_RU);
    }
}
