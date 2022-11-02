package com.klindziuk.sas.pipeline.api;

import com.klindziuk.sas.pipeline.api.handler.HealthHandler;
import com.klindziuk.sas.pipeline.api.router.HealthRouter;
import com.klindziuk.sas.pipeline.model.Health;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HealthRouter.class, HealthHandler.class})
@WebFluxTest
public class HealthRouterTest {

    @Autowired private ApplicationContext context;

    @Autowired private WebTestClient webTestClient;

    @BeforeEach
    public void setUp() {
        webTestClient = WebTestClient.bindToApplicationContext(context).build();
    }

    @Test
    public void healthRouterTest() {
        webTestClient
                .get().uri("/health")
                .exchange()

                .expectStatus()
                .isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(Health.class)
                .isEqualTo(new Health("Healthy!"));

    }
}
