package co.com.mueblessas.dynamodb.gateway;

import co.com.mueblessas.dynamodb.DynamoDBAdapter;
import co.com.mueblessas.model.stat.Stat;
import co.com.mueblessas.model.stat.gateways.StatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class StatRepositoryImp implements StatRepository {

    private final DynamoDBAdapter repository;

    @Override
    public Mono<Stat> save(Stat stat) {
        return repository.save(stat);
    }
}
