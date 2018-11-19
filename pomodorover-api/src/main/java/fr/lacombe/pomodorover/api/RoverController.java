package fr.lacombe.pomodorover.api;

import fr.lacombe.pomodorover.domain.Position;
import fr.lacombe.pomodorover.domain.Rover;
import fr.lacombe.pomodorover.domain.RoverPersistence;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class RoverController {

    private final Rover rover;

    RoverController(RoverPersistence roverPersistence) {
        this.rover = Rover.withInitialPosition(roverPersistence);
    }

    @GetMapping("/position")
    Position getPosition() {
        return rover.getPosition();
    }
}
