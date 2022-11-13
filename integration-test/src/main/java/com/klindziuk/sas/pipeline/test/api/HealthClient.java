package com.klindziuk.sas.pipeline.test.api;

import com.klindziuk.sas.pipeline.test.response.HealthResponse;
import feign.RequestLine;

public interface HealthClient {

  @RequestLine("GET /health")
  HealthResponse healthResponse();

}
