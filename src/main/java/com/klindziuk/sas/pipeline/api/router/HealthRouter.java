package com.klindziuk.sas.pipeline.api.router;

import com.klindziuk.sas.pipeline.api.anno.HealthApiInfo;
import com.klindziuk.sas.pipeline.api.handler.HealthHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.*;

@Configuration
public class HealthRouter {

    @Bean
    @HealthApiInfo
    public RouterFunction<ServerResponse> healthRouterFunction(HealthHandler healthHandler) {
        RequestPredicate healthRoute =
                RequestPredicates.GET("/health")
                        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON));

        return RouterFunctions.route(healthRoute, healthHandler::health);
    }

    @Bean
    public RouterFunction<ServerResponse> helloRouterFunction(HealthHandler healthHandler) {
        RequestPredicate healthRoute =
            RequestPredicates.GET("/")
                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON));

        return RouterFunctions.route(healthRoute, healthHandler::healthTime);
    }
}
