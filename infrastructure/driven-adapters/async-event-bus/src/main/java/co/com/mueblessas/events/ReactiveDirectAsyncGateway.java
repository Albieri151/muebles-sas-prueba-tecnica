package co.com.mueblessas.events;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.reactivecommons.async.api.DirectAsyncGateway;
import org.reactivecommons.async.impl.config.annotations.EnableDirectAsyncGateway;
import reactor.core.publisher.Mono;
import io.cloudevents.CloudEvent;
import io.cloudevents.core.builder.CloudEventBuilder;
import io.cloudevents.jackson.JsonCloudEventData;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.UUID;
import java.net.URI;
import java.time.OffsetDateTime;
import java.util.logging.Level;

@Log
@AllArgsConstructor
@EnableDirectAsyncGateway
public class ReactiveDirectAsyncGateway {
    public static final String TARGET_NAME = "cleanArchitecture";
    public static final String SOME_COMMAND_NAME = "some.command.name";
    public static final String SOME_QUERY_NAME = "some.query.name";
    private final DirectAsyncGateway gateway;
    private final ObjectMapper om;


    public Mono<Void> runRemoteJob(Object command) throws JsonProcessingException {
        log.log(Level.INFO, "Sending command: {0}: {1}", new String[]{SOME_COMMAND_NAME, command.toString()});
        CloudEvent commandCloudEvent = CloudEventBuilder.v1()
                                .withId(UUID.randomUUID().toString())
                                .withSource(URI.create("https://spring.io/foos"))
                                .withType(SOME_COMMAND_NAME)
                                .withTime(OffsetDateTime.now())
                                .withData("application/json", JsonCloudEventData.wrap(om.valueToTree(command)))
                                .build();

        return gateway.sendCommand(commandCloudEvent, TARGET_NAME);
    }

    public Mono<Object> requestForRemoteData(Object query) throws JsonProcessingException {
        log.log(Level.INFO, "Sending query request: {0}: {1}", new String[]{SOME_QUERY_NAME, query.toString()});

        CloudEvent queryCloudEvent = CloudEventBuilder.v1()
                .withId(UUID.randomUUID().toString())
                .withSource(URI.create("https://spring.io/foos"))
                .withType(SOME_QUERY_NAME)
                .withTime(OffsetDateTime.now())
                .withData("application/json", JsonCloudEventData.wrap(om.valueToTree(query)))
                .build();

        return gateway.requestReply(queryCloudEvent, TARGET_NAME, CloudEvent.class)
                .map(cloudEvent -> {
                    log.log(Level.INFO, "Received query response: {0}: {1}", new String[]{SOME_QUERY_NAME, cloudEvent.toString()});
                    return cloudEvent.getData();
                });
    }
}
