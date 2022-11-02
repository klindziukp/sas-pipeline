package com.klindziuk.sas.pipeline.api.handler;

import com.klindziuk.sas.pipeline.model.Health;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Configuration
public class HealthHandler {

    @NonNull
    public Mono<ServerResponse> health(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(new Health().setStatus("Healthy!")), Health.class);
    }
}
