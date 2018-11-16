package fr.lacombe.pomodorover.domain;

import java.util.List;

public interface RoverPersistence {
    void updatePosition(Position initialPosition, List<Command> commands, Position finalPosition);
}
