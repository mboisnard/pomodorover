package fr.lacombe.pomodorover.domain;

import java.util.List;

public class Rover {

    private static final Position DEFAULT_POSITION = Position.of(Orientation.NORTH, Coordinates.of(0, 0));

    private final RoverPersistence roverPersistence;
    private Position position;

    private Rover(RoverPersistence roverPersistence, final Position position) {
        this.position = position;
        this.roverPersistence = roverPersistence;
    }

    public static Rover withInitialPosition(RoverPersistence roverPersistence) {
        Position position = roverPersistence.findLastPosition()
                .orElse(DEFAULT_POSITION);

        return of(roverPersistence, position);
    }

    static Rover of(RoverPersistence roverPersistence, Position position) {
        return new Rover(roverPersistence, position);
    }

    public Position receive(final List<Command> commands) {
        Position initialPosition = position;
        commands.forEach(command -> position = command.execute(position));
        roverPersistence.updatePosition(CommandExecution.of(initialPosition, commands, position));
        return position;
    }

    public Position getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return position.toString();
    }
}
