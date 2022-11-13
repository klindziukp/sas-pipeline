package pipeline;

import java.time.Duration;

import io.gatling.javaapi.core.CoreDsl;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpDsl;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.javaapi.core.CoreDsl.constantUsersPerSec;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class PlayerSimulation extends Simulation {

  HttpProtocolBuilder httpProtocol = HttpDsl.http
      .baseUrl("https://epl-players-lt.herokuapp.com/api/v1/")
      .acceptHeader("application/json")
      .userAgentHeader("Gatling/Performance Test");

  ScenarioBuilder scn = CoreDsl.scenario("Load Test Creating Customers")
      .exec(http("Get all players")
          .get("/players")
          .check(status().is(200)))
      .pause(3,4)

      .exec(http("Get player by id")
          .get("/players/11")
          .check(status().is(200)))
      .pause(3,4);

  public PlayerSimulation() {
    this.setUp(scn.injectOpen(constantUsersPerSec(1).during(Duration.ofSeconds(10))))
        .protocols(httpProtocol);
  }
}