package co.com.mueblessas.model.stat.gateways;

import co.com.mueblessas.model.stat.Stat;
import reactor.core.publisher.Mono;

public interface StatRepository {

    Mono<Stat> save(Stat stat);
}
