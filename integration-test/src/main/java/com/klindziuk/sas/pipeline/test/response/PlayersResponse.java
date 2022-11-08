package com.klindziuk.sas.pipeline.test.response;

import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PlayersResponse {

  Player[] players;

  public List<Player> getPlayers() {
    return List.of(players);
  }
}
