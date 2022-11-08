package com.klindziuk.sas.pipeline.test.api;

import com.klindziuk.sas.pipeline.test.response.Player;
import feign.RequestLine;

public interface PlayerClient {

  @RequestLine("GET /api/v1/players")
  Player[] playersResponse();

}
