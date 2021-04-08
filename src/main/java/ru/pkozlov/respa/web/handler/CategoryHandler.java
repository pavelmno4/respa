package ru.pkozlov.respa.web.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import ru.pkozlov.respa.db.entity.Category;
import ru.pkozlov.respa.dto.category.CategoryDto;
import ru.pkozlov.respa.service.category.CategoryService;
import ru.pkozlov.respa.web.validator.CategoryRequestValidator;

@Component
@RequiredArgsConstructor
public class CategoryHandler {
    private final CategoryService categoryService;
    private final CategoryRequestValidator categoryRequestValidator;

    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public Mono<ServerResponse> save(ServerRequest request) {
        return request
                .bodyToMono(CategoryDto.class)
                .doOnSuccess(categoryRequestValidator::validateRequest)
                .flatMap(categoryRequest -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(
                                categoryService.save(categoryRequest),
                                Category.class
                        ));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public Mono<ServerResponse> findAll(ServerRequest request) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        categoryService.findAll(),
                        Category.class
                );
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public Mono<ServerResponse> deleteById(ServerRequest request) {
        Long categoryId = Long.parseLong(request.pathVariable("id"));

        return ServerResponse
                .ok()
                .body(
                        categoryService.delete(categoryId),
                        Void.class
                );
    }
}
