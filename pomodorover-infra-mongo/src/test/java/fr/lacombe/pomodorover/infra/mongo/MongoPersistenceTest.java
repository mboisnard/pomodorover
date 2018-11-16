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

import static java.util.Collections.singletonList;
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
    void name() {
        MongoPersistence mongoPersistence = MongoPersistence.of(mongoClient);
        when(mongoClient.getDatabase(anyString())).thenReturn(mongoDatabase);
        when(mongoDatabase.getCollection(anyString())).thenReturn(mongoCollection);

        Document document = new Document();

        mongoPersistence.updatePosition(CommandExecution.of(
                Position.of(Orientation.NORTH, Coordinates.of(0, 0)),
                singletonList(Command.FORWARD),
                Position.of(Orientation.NORTH, Coordinates.of(0, 1)))
        );

        verify(mongoCollection).insertOne(document);
    }
}
