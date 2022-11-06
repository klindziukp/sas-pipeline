package com.klindziuk.sas.pipeline.storage;

import com.klindziuk.sas.pipeline.model.Player;
import java.util.Arrays;
import java.util.Random;

public final class PlayerStorage {

  private static final Random RANDOM = new Random();

  public static final Player[] PLAYERS = new Player[]
      {
          new Player().setId(1L).setName("Son Heung-Min").setAge(29)
              .setClub("Tottenham Hotspur").setNationality("South-Korea"),
          new Player().setId(2L).setName("Mohamed Salah").setAge(29)
              .setClub("Liverpool").setNationality("Egypt"),
          new Player().setId(3L).setName("Teemu Pukki").setAge(31)
              .setClub("Norwich City").setNationality("Finland"),
          new Player().setId(4L).setName("Ivan Toney").setAge(25)
              .setClub("Brentford").setNationality("England"),
          new Player().setId(5L).setName("Harry Kane").setAge(32)
              .setClub("Tottenham Hotspur").setNationality("England"),
          new Player().setId(6L).setName("Mason Mount").setAge(22)
              .setClub("Chelsea").setNationality("England")

      };

  public static Player getRandomPlayer() {
    return PLAYERS[RANDOM.nextInt(PLAYERS.length)];
  }

  public static Player[] getPlayersByClub(String club) {
    return Arrays.stream(PLAYERS).filter(
            player -> club.equalsIgnoreCase(player.getClub()))
        .toArray(Player[]::new);
  }

  public static Player[] getPlayersByNationality(String nationality) {
    return Arrays.stream(PLAYERS).filter(
            player -> nationality.equalsIgnoreCase(player.getNationality()))
        .toArray(Player[]::new);
  }
}
