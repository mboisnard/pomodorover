package fr.lacombe.pomodorover.api;

import fr.lacombe.pomodorover.domain.RoverPersistence;
import fr.lacombe.pomodorover.infra.mongo.MongoPersistence;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!test")
public class RoverPersistenceConfiguration {

    @Value("${database.url}")
    private String url;

    @Value("${database.port}")
    private int port;

    @Bean
    public RoverPersistence roverPersistence() {
        return MongoPersistence.of(url, port);
    }
}
