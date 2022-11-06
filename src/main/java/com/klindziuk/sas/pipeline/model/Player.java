package com.klindziuk.sas.pipeline.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Table("player")
public class Player {

  @Id
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

  public Player(Player player) {
    this.name = Objects.requireNonNull(player.getName());
    this.age = Objects.requireNonNull(player.getAge());
    this.club = Objects.requireNonNull(player.getClub());
    this.nationality = Objects.requireNonNull(player.getNationality());
  }
}
