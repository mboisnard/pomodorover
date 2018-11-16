package fr.lacombe.pomodorover.domain;

import java.util.Objects;

public class Position {

    private final Orientation orientation;
    private final Coordinates coordinates;

    private Position(final Orientation orientation, final Coordinates coordinates) {
        this.coordinates = coordinates;
        this.orientation = orientation;
    }

    public static Position of(final Orientation orientation, final Coordinates coordinates) {
        return new Position(orientation, coordinates);
    }

    Position left() {
        return of(orientation.left(), coordinates);
    }

    Position right() {
        return of(orientation.right(), coordinates);
    }

    Position forward() {
        return of(orientation, orientation.forward(coordinates));
    }

    Position backward() {
        return of(orientation, orientation.backward(coordinates));
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Position position = (Position) o;
        return orientation == position.orientation &&
                Objects.equals(coordinates, position.coordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orientation, coordinates);
    }

    @Override
    public String toString() {
        return orientation + " [" + coordinates + "]";
    }
}
