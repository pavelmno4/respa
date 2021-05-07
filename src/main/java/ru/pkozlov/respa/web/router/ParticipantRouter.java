package ru.pkozlov.respa.web.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import ru.pkozlov.respa.web.handler.ParticipantHandler;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class ParticipantRouter {

    @Bean
    public RouterFunction<ServerResponse> participantRoutes(ParticipantHandler handler) {
        return RouterFunctions
                .route()
                .path("/participant", builder -> builder
                        .POST("/save", accept(MediaType.APPLICATION_JSON), handler::save)
                        .GET("/{id}", handler::findById)
                        .GET("/list/{age}/{weightCategory}", handler::findAllInCategory)
                        .DELETE("/{id}", handler::deleteById))
                .build();
    }
}
