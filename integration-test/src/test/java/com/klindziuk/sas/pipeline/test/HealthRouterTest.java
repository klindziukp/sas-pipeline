package com.klindziuk.sas.pipeline.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.klindziuk.sas.pipeline.test.api.HealthService;
import com.klindziuk.sas.pipeline.test.response.HealthResponse;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("Smoke")
public class HealthRouterTest {

  @Test
  @Tag("Smoke")
  public void testHealth() {
    HealthResponse healthResponse = new HealthService().healthClient().healthResponse();
    assertEquals(new HealthResponse().setStatus("Healthy!"), healthResponse);
  }
}
