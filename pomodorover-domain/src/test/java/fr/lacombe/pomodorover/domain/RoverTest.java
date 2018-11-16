package fr.lacombe.pomodorover.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static fr.lacombe.pomodorover.domain.Command.*;
import static fr.lacombe.pomodorover.domain.Orientation.*;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

class RoverTest {

    @ParameterizedTest
    @MethodSource("parameters")
    void should_handle_received_commands(
            final Orientation initialOrientation, final List<Command> commands,
            final Orientation expectedOrientation,
            final int expectedX,
            final int expectedY
    ) {
        final Position position = Position.of(initialOrientation, Coordinates.of(0, 0));
        final Rover rover = Rover.of(position);

        final Position result = rover.receive(commands);

        assertThat(result).isEqualTo(Position.of(expectedOrientation, Coordinates.of(expectedX, expectedY)));
    }

    private static Object[] parameters() {
        return new Object[][] {
                {NORTH, singletonList(FORWARD), NORTH, 0, 1},
                {NORTH,singletonList(LEFT), WEST, 0, 0},
                {NORTH,singletonList(RIGHT), EAST, 0, 0},
                {SOUTH,singletonList(BACKWARD), SOUTH, 0, 1},
                {NORTH, asList(FORWARD, FORWARD, RIGHT, FORWARD), EAST, 1, 2},
                {NORTH, asList(FORWARD, FORWARD, RIGHT, FORWARD, FORWARD, LEFT, BACKWARD, BACKWARD, RIGHT, BACKWARD, LEFT, LEFT, FORWARD, RIGHT), NORTH, 0, 0}
        };
    }
}
