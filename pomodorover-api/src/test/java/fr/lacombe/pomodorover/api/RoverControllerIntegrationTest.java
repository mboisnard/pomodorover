package fr.lacombe.pomodorover.api;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
class RoverControllerIntegrationTest {

    private static final String POSITION_URL = "/position";

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void should_get_the_current_position_of_the_rover() {
        when()
                .get(POSITION_URL)
        .then()
                .statusCode(HttpStatus.OK.value())
                .body("orientation", equalTo("NORTH"))
                .body("coordinates.x", equalTo(0))
                .body("coordinates.y", equalTo(0));
    }
}
