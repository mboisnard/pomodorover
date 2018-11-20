package fr.lacombe.pomodorover.infra.mongo;

import fr.lacombe.pomodorover.domain.CommandExecution;
import fr.lacombe.pomodorover.domain.Position;
import org.bson.Document;

class Adapter {

    static Document toDocument(CommandExecution commandExecution) {
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

    static CommandExecution toCommandExecution(Document document) {

        Position initialPosition = document.get("initialPosition", Position.class);


        return null;
    }
}
