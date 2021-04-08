package ru.pkozlov.respa.service.category;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.pkozlov.respa.db.entity.Category;
import ru.pkozlov.respa.dto.category.CategoryDto;

public interface CategoryService {
    Flux<Category> findAll();

    Mono<Category> save(CategoryDto categoryDto);

    Mono<Void> delete(Long categoryId);
}