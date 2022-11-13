package com.klindziuk.sas.pipeline.test.api;

import com.klindziuk.sas.pipeline.test.response.HealthResponse;
import feign.Feign;
import feign.Logger.Level;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;

public class HealthService {

  public HealthService() {
  }

  public HealthClient healthClient() {
    return Feign.builder()
        .client(new OkHttpClient())
        .encoder(new JacksonEncoder())
        .decoder(new JacksonDecoder())
        .logger(new Slf4jLogger(HealthResponse.class))
        .logLevel(Level.FULL)
        .target(HealthClient.class, "https://epl-players.herokuapp.com/");
  }
}
