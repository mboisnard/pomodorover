package fr.lacombe.pomodorover.api;

import fr.lacombe.pomodorover.domain.Command;

import static fr.lacombe.pomodorover.domain.Command.*;

enum CommandDto {
    F(FORWARD), B(BACKWARD), L(LEFT), R(RIGHT);

    private final Command command;

    CommandDto(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
