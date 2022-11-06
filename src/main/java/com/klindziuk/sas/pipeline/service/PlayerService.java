package com.klindziuk.sas.pipeline.service;

import com.klindziuk.sas.pipeline.model.Player;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PlayerService {

  Mono<Player> getPlayerById(Long id);
  Flux<Player> getAllPlayers();
  Mono<Player> addPlayer(Player playerInput);
  Mono<Player> deletePlayerById(Long id);
}
