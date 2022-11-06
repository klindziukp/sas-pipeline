package com.klindziuk.sas.pipeline.repository;

import com.klindziuk.sas.pipeline.model.Player;
import com.klindziuk.sas.pipeline.storage.PlayerStorage;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class PlayerJpaRepositoryTest extends BaseJpaRepositoryTest {

  @Test
  public void testFindAll() {
    Flux<Player> findAllPlayers = playerRepository.findAll();

    StepVerifier.create(findAllPlayers)
        .expectNext(PlayerStorage.PLAYERS)
        .verifyComplete();
  }

  @Test
  public void testFindByClub() {
    final Player randomPlayer = PlayerStorage.getRandomPlayer();
    final String club = randomPlayer.getClub();
    Flux<Player> findByClubPlayers = playerRepository.findByClub(club);
    Player[] expectedPlayersByClub = PlayerStorage.getPlayersByClub(club);

    StepVerifier.create(findByClubPlayers)
        .expectNext(expectedPlayersByClub)
        .verifyComplete();
  }

  @Test
  public void testFindByNationality() {
    final Player randomPlayer = PlayerStorage.getRandomPlayer();
    final String nationality = randomPlayer.getNationality();

    Flux<Player> findByNationality = playerRepository.findByNationality(nationality);
    Player[] expectedPlayersByNationality = PlayerStorage.getPlayersByNationality(nationality);

    StepVerifier.create(findByNationality)
        .expectNext(expectedPlayersByNationality)
        .verifyComplete();
  }

}
