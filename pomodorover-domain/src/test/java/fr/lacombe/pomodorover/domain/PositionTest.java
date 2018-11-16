package fr.lacombe.pomodorover.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static fr.lacombe.pomodorover.domain.Orientation.*;
import static org.assertj.core.api.Assertions.assertThat;

class PositionTest {

    @ParameterizedTest
    @CsvSource({
            "0, 0, NORTH, 0, 1",
            "0, 1, SOUTH, 0, 0",
            "0, 0, EAST, 1, 0",
            "1, 0, WEST, 0, 0",
    })
    void should_calculate_forward_position_with_orientation(
            final int initialX, final int initialY,
            final Orientation orientation,
            final int expectedX, final int expectedY
    ) {
        final Position position = Position.of(orientation, Coordinates.of(initialX, initialY));

        final Position result = position.forward();

        assertThat(result).isEqualTo(Position.of(orientation, Coordinates.of(expectedX, expectedY)));
    }

    @ParameterizedTest
    @CsvSource({
            "0, 1, NORTH, 0, 0",
            "1, 0, EAST, 0, 0",
            "0, 0, WEST, 1, 0",
            "0, 0, SOUTH, 0, 1",
    })
    void should_calculate_backward_position_with_orientation(
            final int initialX, final int initialY,
            final Orientation orientation,
            final int expectedX, final int expectedY
    ) {
        final Position position = Position.of(orientation, Coordinates.of(initialX, initialY));

        final Position result = position.backward();

        assertThat(result).isEqualTo(Position.of(orientation, Coordinates.of(expectedX, expectedY)));
    }

    @Test
    void should_turn_left() {
        final Position position = Position.of(NORTH, Coordinates.of(0, 0));

        final Position result = position.left();

        assertThat(result).isEqualTo(Position.of(WEST, Coordinates.of(0, 0)));
    }

    @Test
    void should_turn_right() {
        final Position position = Position.of(NORTH, Coordinates.of(0, 0));

        final Position result = position.right();

        assertThat(result).isEqualTo(Position.of(EAST, Coordinates.of(0, 0)));
    }
}
