package com.klindziuk.sas.pipeline.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.klindziuk.sas.pipeline.test.api.PlayerService;
import com.klindziuk.sas.pipeline.test.response.Player;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("Smoke")
public class PlayerControllerTest {

  @Test
  @Tag("Smoke")
  public void testGetAllPlayers() {
    Player[] players = new PlayerService().playerClient().playersResponse();
    assertEquals(6, players.length);
  }
}
