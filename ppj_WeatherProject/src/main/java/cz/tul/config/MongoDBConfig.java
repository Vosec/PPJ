package cz.tul.config;

import cz.tul.model.Measurement;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.UncategorizedMongoDbException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;

//https://docs.mongodb.com/manual/tutorial/expire-data/
@Configuration
public class MongoDBConfig implements InitializingBean {
    private static final String TTL_INDEX = "TTL_INDEX";

    @Value("${measurement.expireAfter}")
    private int expiration;

    private MongoTemplate mongoTemplate;

    @Autowired
    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public MongoDBConfig() {
    }

    //Create index on attribute saveTime (Its Date created after Measurement has been created)
    @Override
    public void afterPropertiesSet() {
        //System.out.println(expiration);
        try {
            mongoTemplate.indexOps(Measurement.class).ensureIndex(new Index().on("saveTime", Sort.Direction.ASC).expire(expiration).named(TTL_INDEX));
        } catch (UncategorizedMongoDbException e) {
            mongoTemplate.indexOps(Measurement.class).dropIndex(TTL_INDEX);
            mongoTemplate.indexOps(Measurement.class).ensureIndex(new Index().on("saveTime", Sort.Direction.ASC).expire(expiration).named(TTL_INDEX));
        }
    }
}
