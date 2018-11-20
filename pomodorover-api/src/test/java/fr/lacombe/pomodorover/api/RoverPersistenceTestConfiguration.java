package fr.lacombe.pomodorover.api;

import fr.lacombe.pomodorover.domain.RoverPersistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Mockito.mock;

@Configuration
public class RoverPersistenceTestConfiguration {

    @Bean
    public RoverPersistence roverPersistence() {
        return mock(RoverPersistence.class);
    }
}
