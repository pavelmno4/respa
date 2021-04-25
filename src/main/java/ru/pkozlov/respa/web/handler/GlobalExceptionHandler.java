package ru.pkozlov.respa.web.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;
import ru.pkozlov.respa.dto.ErrorResponseDto;
import ru.pkozlov.respa.exception.RequestValidationException;
import ru.pkozlov.respa.service.ResourceBundleMessageService;

@Slf4j
@Configuration
@Order(-2)
public class GlobalExceptionHandler extends AbstractErrorWebExceptionHandler {
    private final ResourceBundleMessageService messageService;

    public GlobalExceptionHandler(ErrorAttributes errorAttributes,
                                  WebProperties.Resources resources,
                                  ApplicationContext applicationContext,
                                  ServerCodecConfigurer serverCodecConfigurer,
                                  ResourceBundleMessageService messageService) {
        super(errorAttributes, resources, applicationContext);
        this.setMessageWriters(serverCodecConfigurer.getWriters());
        this.messageService = messageService;
    }

    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        return RouterFunctions.route(RequestPredicates.all(), this::handleError);
    }

    private Mono<ServerResponse> handleError(ServerRequest request) {
        Throwable t = super.getError(request);
        logError(request, t);
        ErrorResponseDto errorResponseDto = new ErrorResponseDto();

        if (t instanceof RequestValidationException) {
            RequestValidationException exception = (RequestValidationException) t;

            if (exception.getDetails() != null && exception.getDetails().length > 0) {
                errorResponseDto.setMessage(messageService.getMessage(exception.getReason()));
                errorResponseDto.setDetails(exception.getDetails());
                return ServerResponse.status(HttpStatus.BAD_REQUEST).body(BodyInserters.fromValue(errorResponseDto));
            }

            errorResponseDto.setMessage(messageService.getMessage(exception.getReason()));
            return ServerResponse.status(HttpStatus.BAD_REQUEST).body(BodyInserters.fromValue(errorResponseDto));
        }

        errorResponseDto.setMessage(messageService.getMessage("exception.unknown"));
        return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BodyInserters.fromValue(errorResponseDto));
    }

    private void logError(ServerRequest request, Throwable throwable) {
        log.debug("Exception processing request: {}", request.uri());
        log.error("Exception message: {}", throwable.getMessage());
        log.debug("Exception detail: ", throwable);
    }
}
