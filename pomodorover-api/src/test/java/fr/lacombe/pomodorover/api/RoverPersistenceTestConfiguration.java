package fr.lacombe.pomodorover.api;

import fr.lacombe.pomodorover.domain.RoverPersistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import static org.mockito.Mockito.mock;

@Profile("test")
@Configuration
public class RoverPersistenceTestConfiguration {

    @Bean
    public RoverPersistence roverPersistence() {
        return mock(RoverPersistence.class);
    }
}
