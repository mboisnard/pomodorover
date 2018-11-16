package fr.lacombe.pomodorover.infra.mongo;

import com.mongodb.MongoClient;
import fr.lacombe.pomodorover.domain.CommandExecution;
import fr.lacombe.pomodorover.domain.RoverPersistence;
import org.bson.Document;

public class MongoPersistence implements RoverPersistence {

    private final MongoClient mongoClient;

    private MongoPersistence(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    public static MongoPersistence of(MongoClient mongoClient) {
        return new MongoPersistence(mongoClient);
    }

    @Override
    public void updatePosition(CommandExecution commandExecution) {
        var mongoDatabase = mongoClient.getDatabase("rover");
        var positionsCollection = mongoDatabase.getCollection("positions");
        positionsCollection.insertOne(new Document());
    }
}
