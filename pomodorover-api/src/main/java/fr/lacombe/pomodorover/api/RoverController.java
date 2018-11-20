package fr.lacombe.pomodorover.api;

import fr.lacombe.pomodorover.domain.Command;
import fr.lacombe.pomodorover.domain.Position;
import fr.lacombe.pomodorover.domain.Rover;
import fr.lacombe.pomodorover.domain.RoverPersistence;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/position")
class RoverController {

    private final Rover rover;

    RoverController(RoverPersistence roverPersistence) {
        this.rover = Rover.withInitialPosition(roverPersistence);
    }

    @GetMapping
    Position getPosition() {
        return rover.getPosition();
    }

    @PutMapping
    Position updatePosition(@RequestBody List<CommandDto> commandDtos) {

        List<Command> commands = commandDtos.stream()
            .map(CommandDto::getCommand)
            .collect(toList());

        return rover.receive(commands);
    }
}
