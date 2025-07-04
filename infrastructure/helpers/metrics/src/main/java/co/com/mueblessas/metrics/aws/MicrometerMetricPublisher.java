package co.com.mueblessas.metrics.aws;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.metrics.MetricCollection;
import software.amazon.awssdk.metrics.MetricPublisher;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class MicrometerMetricPublisher implements MetricPublisher {
    private final ExecutorService service = Executors.newFixedThreadPool(10);
    private final MeterRegistry registry;

    @Override
    public void publish(MetricCollection metricCollection) {
        service.submit(() -> {
            List<Tag> tags = buildTags(metricCollection);
            metricCollection.stream()
                    .filter(record -> record.value() instanceof Duration || record.value() instanceof Integer)
                    .forEach(record -> {
                        if (record.value() instanceof Duration) {
                            registry.timer(record.metric().name(), tags).record((Duration) record.value());
                        } else if (record.value() instanceof Integer) {
                            registry.counter(record.metric().name(), tags).increment((Integer) record.value());
                        }
                    });
        });
    }

    @Override
    public void close() {

    }

    private List<Tag> buildTags(MetricCollection metricCollection) {
        return metricCollection.stream()
                .filter(record -> record.value() instanceof String || record.value() instanceof Boolean)
                .map(record -> Tag.of(record.metric().name(), record.value().toString()))
                .collect(Collectors.toList());
    }
}
