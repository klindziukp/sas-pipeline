package com.klindziuk.sas.pipeline.service;

import com.klindziuk.sas.pipeline.exception.EntityMappingException;
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
  public void getPlayerByIdServiceTest() {
    final Player randomPlayer = PlayerStorage.getRandomPlayer();
    Mockito.when(playerRepository.findById(randomPlayer.getId()))
        .thenReturn(Mono.just(randomPlayer));

    Mono<Player> findByIdPlayer = playerRepository.findById(randomPlayer.getId());

    StepVerifier.create(findByIdPlayer)
        .expectNext(randomPlayer)
        .verifyComplete();
  }

  @Test
  public void addPlayerServiceTest() {
    Player playerToCreate = new Player().setName("test-player").setAge(43).setClub("test-club")
        .setNationality("test-nationality");
    Mockito.when(playerRepository.save(playerToCreate))
        .thenReturn(Mono.just(playerToCreate.setId(77L)));

    Mono<Player> savedPlayer = playerRepository.save(playerToCreate);

    StepVerifier.create(savedPlayer)
        .expectNext(playerToCreate)
        .verifyComplete();
  }

  @Test
  public void notFoundPlayerServiceTest() {
    final long notFoundId = 77L;
    final String errorMessage = "There is no player with id: '77'";
    Mockito.when(playerRepository.findById(notFoundId))
        .thenReturn(Mono.error(new EntityMappingException(errorMessage)));

    StepVerifier
        .create(playerRepository.findById(notFoundId))
        .expectErrorMatches(throwable -> throwable instanceof EntityMappingException &&
            throwable.getMessage().equals(errorMessage))
        .verify();
  }
}
