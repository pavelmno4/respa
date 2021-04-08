package ru.pkozlov.respa.web.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import ru.pkozlov.respa.web.handler.CategoryHandler;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class CategoryRouter {

    @Bean
    public RouterFunction<ServerResponse> categoryRotes(CategoryHandler handler) {
        return RouterFunctions
                .route()
                .path("/category", builder -> builder
                        .GET("/findAll", handler::findAll)
                        .POST("/save", accept(MediaType.APPLICATION_JSON), handler::save)
                        .DELETE("/{id}", handler::deleteById))
                .build();
    }
}
