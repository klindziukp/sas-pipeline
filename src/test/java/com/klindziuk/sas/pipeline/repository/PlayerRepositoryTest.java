package com.klindziuk.sas.pipeline.repository;

import com.klindziuk.sas.pipeline.model.Player;
import com.klindziuk.sas.pipeline.storage.PlayerStorage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@DataR2dbcTest
@ExtendWith(SpringExtension.class)
public class PlayerRepositoryTest {
  @Autowired
  PlayerRepository playerRepository;

  @Test
  public void testFindAll() {
    Flux<Player> findAllPlayers = playerRepository.findAll();

    StepVerifier.create(findAllPlayers)
        .expectNext(PlayerStorage.PLAYERS)
        .verifyComplete();
  }

  @Test
  public void testFindById() {
    final Player randomPlayer = PlayerStorage.getRandomPlayer();
    Mono<Player> findByIdPlayer = playerRepository.findById(randomPlayer.getId());

    StepVerifier.create(findByIdPlayer)
        .expectNext(randomPlayer)
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
