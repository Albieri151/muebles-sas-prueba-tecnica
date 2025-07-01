package co.com.mueblessas.dynamodb.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;
import software.amazon.awssdk.metrics.MetricPublisher;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class DynamoDBConfigTest {

    private DynamoDBConfig config;

    @BeforeEach
    void setUp() {
        config = new DynamoDBConfig();
    }

    @Test
    void shouldCreateDynamoDbAsyncClient() {
        String testEndpoint = "http://localhost:8000";
        String testRegion = "us-west-2";

        DynamoDbAsyncClient client = config.amazonDynamoDB(testEndpoint, testRegion);

        assertThat(client).isNotNull();
        assertThat(client.serviceName()).isEqualTo("dynamodb");
    }

    @Test
    void shouldCreateDynamoDbEnhancedAsyncClient() {
        DynamoDbAsyncClient mockClient = mock(DynamoDbAsyncClient.class);

        DynamoDbEnhancedAsyncClient enhancedClient = config.getDynamoDbEnhancedAsyncClient(mockClient);

        assertThat(enhancedClient).isNotNull();
    }
}

