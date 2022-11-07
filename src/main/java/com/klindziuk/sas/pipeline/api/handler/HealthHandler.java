package com.klindziuk.sas.pipeline.api.handler;

import com.klindziuk.sas.pipeline.model.DoNothing;
import com.klindziuk.sas.pipeline.model.Health;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneOffset;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Configuration
public class HealthHandler {

  @NonNull
  public Mono<ServerResponse> health(ServerRequest request) {
    return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
        .body(Mono.just(new Health().setStatus("Healthy!")), Health.class);
  }

  @NonNull
  @SuppressWarnings("deprecation")
  public Mono<ServerResponse> healthTime(ServerRequest request) {
    return
        ServerResponse.ok().contentType(MediaType.APPLICATION_STREAM_JSON)
            .body(doNothingFlux(), DoNothing.class);
  }

  private Flux<DoNothing> doNothingFlux() {
    int startTime = Instant.now().atZone(ZoneOffset.UTC).getSecond();
    return Flux.interval(Duration.ofSeconds(2))
        .map(i -> new DoNothing(String.format("You are doing nothing for '%s' seconds",
            Instant.now().atZone(ZoneOffset.UTC).getSecond() - startTime)));
  }
}
