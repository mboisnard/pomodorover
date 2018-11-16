package fr.lacombe.pomodorover.domain;

import java.util.List;

class Rover {

    private Position position;

    private Rover(final Position position) {
        this.position = position;
    }

    static Rover withInitialPosition() {
        return new Rover(Position.of(Orientation.NORTH, Coordinates.of(0, 0)));
    }

    static Rover of(final Position position) {
        return new Rover(position);
    }

    Position receive(final List<Command> commands) {
        commands.forEach(command -> position = command.execute(position));
        return position;
    }

    @Override
    public String toString() {
        return position.toString();
    }
}
