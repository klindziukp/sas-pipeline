package com.klindziuk.sas.pipeline.test.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Player {

  @JsonProperty("id")
  private Long id;
  @JsonProperty("name")
  private String name;
  @JsonProperty("age")
  private Integer age;
  @JsonProperty("club")
  private String club;
  @JsonProperty("nationality")
  private String nationality;

}
