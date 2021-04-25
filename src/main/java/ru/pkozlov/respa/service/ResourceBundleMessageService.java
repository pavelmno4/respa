package ru.pkozlov.respa.service;

public interface ResourceBundleMessageService {
    String getMessage(String code);

    String getMessage(String code, Object... args);
}
