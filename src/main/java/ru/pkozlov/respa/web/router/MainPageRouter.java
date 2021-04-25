package ru.pkozlov.respa.web.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;


// Plug
@Configuration
public class MainPageRouter {

    @Bean
    public RouterFunction<ServerResponse> mainPageRoutes() {
        return RouterFunctions
                .route()
                .path("/", builder -> builder
                        .GET("/", this::handle))
                .build();
    }

    private Mono<ServerResponse> handle(ServerRequest request) {
        return ServerResponse
                .ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(
                        Mono.just("Hi"),
                        String.class
                );
    }
}
