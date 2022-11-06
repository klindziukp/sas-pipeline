package com.klindziuk.sas.pipeline.service;

import com.klindziuk.sas.pipeline.model.Player;
import com.klindziuk.sas.pipeline.repository.PlayerRepository;
import com.klindziuk.sas.pipeline.storage.PlayerStorage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {PlayerServiceImpl.class})
public class PlayerServiceTest {

  @Autowired
  private ApplicationContext context;

  @Autowired
  private PlayerService playerService;

  @MockBean
  private PlayerRepository playerRepository;


  @Test
  public void getAllPlayersRouterTest() {
    Mockito.when(playerRepository.findAll()).thenReturn(Flux.just(PlayerStorage.PLAYERS));

    Flux<Player> findAllPlayers = playerService.getAllPlayers();

    StepVerifier.create(findAllPlayers)
        .expectNext(PlayerStorage.PLAYERS)
        .verifyComplete();
  }

  @Test
  public void getPlayerByIdRouterTest() {
    final Player randomPlayer = PlayerStorage.getRandomPlayer();
    Mockito.when(playerRepository.findById(randomPlayer.getId()))
        .thenReturn(Mono.just(randomPlayer));

    Mono<Player> findByIdPlayer = playerRepository.findById(randomPlayer.getId());

    StepVerifier.create(findByIdPlayer)
        .expectNext(randomPlayer)
        .verifyComplete();
  }
}
