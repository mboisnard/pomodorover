package fr.lacombe.pomodorover.domain;

import java.util.List;
import java.util.Objects;

public class CommandExecution {
    private final Position initialPosition;
    private final List<Command> commands;
    private final Position finalPosition;

    private CommandExecution(Position initialPosition, List<Command> commands, Position finalPosition) {
        this.initialPosition = initialPosition;
        this.commands = commands;
        this.finalPosition = finalPosition;
    }

    static CommandExecution of(Position initialPosition, List<Command> commands, Position finalPosition) {
        return new CommandExecution(initialPosition, commands, finalPosition);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommandExecution that = (CommandExecution) o;
        return Objects.equals(initialPosition, that.initialPosition) &&
                Objects.equals(commands, that.commands) &&
                Objects.equals(finalPosition, that.finalPosition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(initialPosition, commands, finalPosition);
    }

    @Override
    public String toString() {
        return "CommandExecution{" +
                "initialPosition=" + initialPosition +
                ", commands=" + commands +
                ", finalPosition=" + finalPosition +
                '}';
    }
}
