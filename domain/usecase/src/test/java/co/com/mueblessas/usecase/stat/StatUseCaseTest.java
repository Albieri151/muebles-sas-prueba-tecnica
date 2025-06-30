package co.com.mueblessas.usecase.stat;

import co.com.mueblessas.model.stat.Stat;
import co.com.mueblessas.usecase.stat.utils.HashUtils;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;

public class StatUseCaseTest {

    @Test
    void testHashValidation() {
        Stat stat = new Stat(LocalDateTime.of(2024, 6, 30, 15, 45, 0)
                , 250, 25, 10, 100, 100, 7, 2, "da3c9ed32fe3754bc72194e9a40cf062");
        HashUtils.isHashValid(stat)
                .as(StepVerifier::create)
                .expectNext(true)
                .verifyComplete();
    }
}
