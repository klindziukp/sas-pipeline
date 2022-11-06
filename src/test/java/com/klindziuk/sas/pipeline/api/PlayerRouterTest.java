package com.klindziuk.sas.pipeline.api;

import com.klindziuk.sas.pipeline.api.handler.PlayerHandler;
import com.klindziuk.sas.pipeline.api.router.PlayerRouter;
import com.klindziuk.sas.pipeline.model.Player;
import com.klindziuk.sas.pipeline.service.PlayerService;
import com.klindziuk.sas.pipeline.storage.PlayerStorage;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {PlayerRouter.class, PlayerService.class, PlayerHandler.class})
@WebFluxTest
@Slf4j
public class PlayerRouterTest {

  @Autowired
  private ApplicationContext context;

  @Autowired
  private WebTestClient webTestClient;

  @MockBean
  private PlayerService playerService;

  @BeforeEach
  public void setUp() {
    webTestClient = WebTestClient.bindToApplicationContext(context).build();
  }

  @Test
  public void getAllPlayersRouterTest() {
    Mockito.when(playerService.getAllPlayers()).thenReturn(Flux.just(PlayerStorage.PLAYERS));

    webTestClient
        .get().uri("/players")
        .exchange()
        .expectStatus()
        .isOk()
        .expectHeader().contentType(MediaType.APPLICATION_JSON)
        .expectBodyList(Player.class)
        .isEqualTo(List.of(PlayerStorage.PLAYERS));

  }

  @Test
  public void getPlayerByIdRouterTest() {
    Player randomPlayer = PlayerStorage.getRandomPlayer();
    Mockito.when(playerService.getPlayerById(randomPlayer.getId()))
        .thenReturn(Mono.just(randomPlayer));

    final String playerByIdPath = "/players/" + randomPlayer.getId();
    webTestClient
        .get().uri(playerByIdPath)
        .exchange()
        .expectStatus()
        .isOk()
        .expectHeader().contentType(MediaType.APPLICATION_JSON)
        .expectBody(Player.class)
        .isEqualTo(randomPlayer);

  }

  @Test
  public void addPlayerRouterTest() {
    Player playerToCreate = new Player().setName("test-player").setAge(43).setClub("test-club")
        .setNationality("test-nationality");
    Mockito.when(playerService.addPlayer(playerToCreate))
        .thenReturn(Mono.just(playerToCreate.setId(77L)));

    webTestClient
        .post().uri("/players")
        .body(Mono.just(playerToCreate), Player.class)
        .exchange()
        .expectStatus()
        .isOk()
        .expectHeader().contentType(MediaType.APPLICATION_JSON)
        .expectBody(Player.class)
        .isEqualTo(playerToCreate);
  }

  @Test
  public void deletePlayerByIdRouterTest() {
    Player randomPlayer = PlayerStorage.getRandomPlayer();
    Mockito.when(playerService.deletePlayerById(randomPlayer.getId()))
        .thenReturn(Mono.just(randomPlayer));

    final String playerByIdPath = "/players/" + randomPlayer.getId();
    webTestClient
        .delete().uri(playerByIdPath)
        .exchange()
        .expectStatus()
        .isOk()
        .expectHeader().contentType(MediaType.APPLICATION_JSON)
        .expectBody(Player.class)
        .isEqualTo(randomPlayer);
  }
}
