package com.klindziuk.sas.pipeline.test.api;

import com.klindziuk.sas.pipeline.test.response.Player;
import feign.Feign;
import feign.Logger;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;

public class PlayerService {

  public PlayerService() {
  }

  public PlayerClient playerClient() {
    return Feign.builder()
        .client(new OkHttpClient())
        .encoder(new JacksonEncoder())
        .decoder(new JacksonDecoder())
        .logger(new Slf4jLogger(Player.class))
        .logLevel(Logger.Level.FULL)
        .target(PlayerClient.class, "https://epl-players.herokuapp.com/");
  }
}
