package co.com.mueblessas.usecase.stat;

import co.com.mueblessas.model.events.gateways.EventsGateway;
import co.com.mueblessas.model.stat.Stat;
import co.com.mueblessas.model.stat.gateways.StatRepository;
import co.com.mueblessas.usecase.stat.utils.HashUtils;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import java.time.LocalDateTime;


@RequiredArgsConstructor
public class StatUseCase {

    private final StatRepository statRepository;
    private final EventsGateway eventsGateway;

    public Mono<Stat> save(Stat stat) {
        return HashUtils.isHashValid(stat)
                .flatMap(isValid -> {
                    if (!isValid) {
                        return Mono.error(new IllegalArgumentException("Invalid hash"));
                    }
                    stat.setTimestamp(LocalDateTime.now());
                    return statRepository.save(stat)
                            .flatMap(saved -> eventsGateway.emit(saved).thenReturn(saved));
                    });
    }
}
