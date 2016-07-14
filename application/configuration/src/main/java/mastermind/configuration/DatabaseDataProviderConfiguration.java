package mastermind.configuration;

import mastermind.core.usecase.GetAllExamples;
import mastermind.dataproviders.GetAllExamplesFromDatabaseDataProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class DatabaseDataProviderConfiguration {

    @Bean
    public GetAllExamples getAllExamples(MongoTemplate mongoTemplate) {
        return new GetAllExamplesFromDatabaseDataProvider(mongoTemplate);
    }
}
