package fr.lacombe.pomodorover.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class OrientationTest {

    @ParameterizedTest
    @CsvSource({
            "NORTH, WEST",
            "WEST, SOUTH",
            "SOUTH, EAST",
            "EAST, NORTH"
    })
    void should_have_the_correct_orientation_when_turning_left(final Orientation initialOrientation, final Orientation expectedOrientation) {
        final Orientation result = initialOrientation.left();

        assertThat(result).isEqualTo(expectedOrientation);
    }

    @ParameterizedTest
    @CsvSource({
            "NORTH, EAST",
            "EAST, SOUTH",
            "SOUTH, WEST",
            "WEST, NORTH"
    })
    void should_have_the_correct_orientation_when_turning_right(final Orientation initialOrientation, final Orientation expectedOrientation) {
        final Orientation result = initialOrientation.right();

        assertThat(result).isEqualTo(expectedOrientation);
    }
}
