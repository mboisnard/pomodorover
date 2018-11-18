module fr.lacombe.pomodorover.infra.mongo {
    requires mongo.java.driver;
    requires fr.lacombe.pomodorover.domain;

    opens fr.lacombe.pomodorover.infra.mongo;
}