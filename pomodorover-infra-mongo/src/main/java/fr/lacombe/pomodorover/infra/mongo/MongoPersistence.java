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

    static MongoPersistence of(MongoClient mongoClient) {
        return new MongoPersistence(mongoClient);
    }

    @Override
    public void updatePosition(CommandExecution commandExecution) {
        var document = mapDocument(commandExecution);

        var mongoDatabase = mongoClient.getDatabase("rover");
        var positionsCollection = mongoDatabase.getCollection("positions");

        positionsCollection.insertOne(document);
    }

    Document mapDocument(CommandExecution commandExecution) {
        var initialPosition = new Document()
                .append("orientation", commandExecution.getInitialPosition().getOrientation())
                .append("x", commandExecution.getInitialPosition().getCoordinates().getX())
                .append("y", commandExecution.getInitialPosition().getCoordinates().getY());

        var commands = new Document()
                .append("commands", commandExecution.getCommands());

        var finalPosition = new Document()
                .append("orientation", commandExecution.getFinalPosition().getOrientation())
                .append("x", commandExecution.getFinalPosition().getCoordinates().getX())
                .append("y", commandExecution.getFinalPosition().getCoordinates().getY());

        return new Document()
                .append("initialPosition", initialPosition)
                .append("commands", commands)
                .append("finalPosition", finalPosition);
    }
}
