module fr.lacombe.pomodorover.api {
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.beans;
    requires spring.boot;
    requires spring.web;
    requires java.sql;

    requires fr.lacombe.pomodorover.domain;
    requires fr.lacombe.pomodorover.infra.mongo;

    exports fr.lacombe.pomodorover.api to spring.beans, spring.context, spring.web;
    exports fr.lacombe.pomodorover to spring.beans, spring.context, spring.web;

    opens fr.lacombe.pomodorover.api to spring.core;
    opens fr.lacombe.pomodorover to spring.core;
}
