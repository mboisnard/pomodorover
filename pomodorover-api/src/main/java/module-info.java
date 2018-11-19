module fr.lacombe.pomodorover.api {
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.beans;
    requires spring.boot;
    requires spring.web;
    requires fr.lacombe.pomodorover.domain;
    requires fr.lacombe.pomodorover.infra.mongo;

    exports fr.lacombe.pomodorover.api to spring.beans, spring.context, spring.web;

    opens fr.lacombe.pomodorover.api to spring.core;
}