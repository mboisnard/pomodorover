package fr.lacombe.pomodorover.infra.mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import fr.lacombe.pomodorover.domain.CommandExecution;
import fr.lacombe.pomodorover.domain.Position;
import fr.lacombe.pomodorover.domain.RoverPersistence;
import org.bson.Document;

import java.util.Optional;

import static fr.lacombe.pomodorover.infra.mongo.Adapter.toDocument;

public class MongoPersistence implements RoverPersistence {

    private final MongoClient mongoClient;

    MongoPersistence(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    public static MongoPersistence of(String url, int port) {
        MongoClient mongoClient = new MongoClient(url, port);
        return new MongoPersistence(mongoClient);
    }

    @Override
    public void updatePosition(CommandExecution commandExecution) {
        var document = toDocument(commandExecution);

        var mongoDatabase = mongoClient.getDatabase("rover");
        var positionsCollection = mongoDatabase.getCollection("positions");

        positionsCollection.insertOne(document);
    }

    @Override
    public Optional<Position> findLastPosition() {
        var mongoDatabase = mongoClient.getDatabase("rover");
        var positionsCollection = mongoDatabase.getCollection("positions");

        Document lastPosition = positionsCollection
                .find()
                .sort(new BasicDBObject("$natural", -1))
                .first();
        throw new UnsupportedOperationException();
    }

}
