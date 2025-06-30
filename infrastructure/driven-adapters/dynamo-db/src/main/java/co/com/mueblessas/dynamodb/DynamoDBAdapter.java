package co.com.mueblessas.dynamodb;

import co.com.mueblessas.dynamodb.helper.AdapterOperations;
import co.com.mueblessas.model.stat.Stat;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;


@Repository
public class DynamoDBAdapter extends AdapterOperations<Stat, co.com.mueblessas.dynamodb.model.Stat> {

    public DynamoDBAdapter(DynamoDbEnhancedAsyncClient dynamoDbEnhancedAsyncClient,
                           ObjectMapper mapper) {
        super(dynamoDbEnhancedAsyncClient,
                mapper,
                "Stat");
    }
}