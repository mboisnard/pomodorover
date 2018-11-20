package fr.lacombe.pomodorover.infra.mongo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import fr.lacombe.pomodorover.domain.*;
import org.bson.Document;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static fr.lacombe.pomodorover.domain.Command.*;
import static fr.lacombe.pomodorover.domain.Orientation.NORTH;
import static fr.lacombe.pomodorover.infra.mongo.Adapter.toDocument;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MongoPersistenceTest {

    @Mock
    private MongoClient mongoClient;
    @Mock
    private MongoDatabase mongoDatabase;
    @Mock
    private MongoCollection<Document> mongoCollection;

    @Test
    void should_insert_a_command_execution_as_bson_document() {
        MongoPersistence mongoPersistence = new MongoPersistence(mongoClient);
        when(mongoClient.getDatabase(anyString())).thenReturn(mongoDatabase);
        when(mongoDatabase.getCollection(anyString())).thenReturn(mongoCollection);

        var document = document();

        mongoPersistence.updatePosition(commandExecution());

        verify(mongoCollection).insertOne(document);
    }

    @Test
    void should_map_command_execution_as_bson_document() {
        Document document = toDocument(commandExecution());

        assertThat(document).isEqualTo(document());
    }

    private CommandExecution commandExecution() {
        return CommandExecution.of(
                Position.of(NORTH, Coordinates.of(0, 0)),
                singletonList(FORWARD),
                Position.of(NORTH, Coordinates.of(0, 1))
        );
    }

    private Document document() {
        var initialPosition = new Document()
            .append("orientation", NORTH)
            .append("x", 0)
            .append("y", 0);

        var commands = new Document()
                .append("commands", singletonList(FORWARD));

        var finalPosition = new Document()
                .append("orientation", NORTH)
                .append("x", 0)
                .append("y", 1);

        return new Document()
                .append("initialPosition", initialPosition)
                .append("commands", commands)
                .append("finalPosition", finalPosition);
    }
}
