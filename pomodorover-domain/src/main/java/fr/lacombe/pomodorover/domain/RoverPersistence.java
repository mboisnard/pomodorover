package fr.lacombe.pomodorover.domain;

import java.util.Optional;

public interface RoverPersistence {
    void updatePosition(CommandExecution commandExecution);

    Optional<Position> findLastPosition();
}
