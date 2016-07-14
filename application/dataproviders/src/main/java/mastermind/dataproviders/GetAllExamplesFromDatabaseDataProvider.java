package mastermind.dataproviders;

import mastermind.core.domain.Example;
import mastermind.core.usecase.GetAllExamples;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

public class GetAllExamplesFromDatabaseDataProvider implements GetAllExamples {

    private MongoTemplate mongoTemplate;

    public GetAllExamplesFromDatabaseDataProvider(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<Example> getAllExamples() {
        return mongoTemplate.findAll(Example.class, "examples");
    }
}
