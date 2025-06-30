package co.com.mueblessas.api;

import co.com.mueblessas.model.stat.Stat;
import co.com.mueblessas.usecase.stat.StatUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import java.net.URI;

@RestController
@RequiredArgsConstructor
public class Handler {

    private final StatUseCase useCase;

    public Mono<ServerResponse> saveStat(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(Stat.class)
                .flatMap(useCase::save)
                .flatMap(savedStat -> ServerResponse
                        .created(URI.create("/stats/" + savedStat.getTimestamp()))
                        .bodyValue(savedStat))
                .onErrorResume(ex -> {
                    return ServerResponse
                            .badRequest()
                            .bodyValue("Error al procesar la solicitud: " + ex.getMessage());
                });
    }
}

