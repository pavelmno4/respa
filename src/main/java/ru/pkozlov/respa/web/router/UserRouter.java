package ru.pkozlov.respa.web.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import ru.pkozlov.respa.web.handler.UserHandler;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class UserRouter {

    @Bean
    public RouterFunction<ServerResponse> userRoutes(UserHandler handler) {
        return RouterFunctions
                .route()
                .path("/user", builder -> builder
                        .POST("/save", accept(MediaType.APPLICATION_JSON), handler::save)
                        .GET("/{id}", handler::findById)
                        .DELETE("/{id}", handler::deleteById))
                .build();
    }
}
