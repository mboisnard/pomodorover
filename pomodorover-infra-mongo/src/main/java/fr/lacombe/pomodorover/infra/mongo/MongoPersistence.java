package fr.lacombe.pomodorover.infra.mongo;

import com.mongodb.MongoClient;
import fr.lacombe.pomodorover.domain.CommandExecution;
import fr.lacombe.pomodorover.domain.Position;
import fr.lacombe.pomodorover.domain.RoverPersistence;
import org.bson.Document;

import java.util.Optional;

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
        var document = mapDocument(commandExecution);

        var mongoDatabase = mongoClient.getDatabase("rover");
        var positionsCollection = mongoDatabase.getCollection("positions");

        positionsCollection.insertOne(document);
    }

    @Override
    public Optional<Position> findLastPosition() {
        throw new UnsupportedOperationException();
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
