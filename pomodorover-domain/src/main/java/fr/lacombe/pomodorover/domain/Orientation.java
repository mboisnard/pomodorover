package fr.lacombe.pomodorover.domain;

public enum Orientation {

    NORTH(0, 1),
    EAST(1, 0),
    SOUTH(0, -1),
    WEST(-1, 0);

    private final int offsetX;
    private final int offsetY;

    Orientation(final int offsetX, final int offsetY) {
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

    Orientation left() {
        if (this == NORTH)
            return WEST;
        return Orientation.values()[this.ordinal() - 1];
    }

    Orientation right() {
        if (this == WEST)
            return NORTH;
        return Orientation.values()[this.ordinal() + 1];
    }

    Coordinates forward(final Coordinates coordinates) {
        return coordinates.add(offsetX, offsetY);
    }

    Coordinates backward(final Coordinates coordinates) {
        return coordinates.subtract(offsetX, offsetY);
    }
}
