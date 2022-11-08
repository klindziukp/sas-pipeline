package com.klindziuk.sas.pipeline.api.router;

import com.klindziuk.sas.pipeline.api.anno.AddPlayerApiInfo;
import com.klindziuk.sas.pipeline.api.anno.DeletePlayerByIdApiInfo;
import com.klindziuk.sas.pipeline.api.anno.GetAllPlayersApiInfo;
import com.klindziuk.sas.pipeline.api.anno.GetPlayerByIdApiInfo;
import com.klindziuk.sas.pipeline.api.handler.PlayerHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class PlayerRouter {

  @Bean
  @GetAllPlayersApiInfo
  public RouterFunction<ServerResponse> getAllPlayersRouterFunction(PlayerHandler playerHandler) {
    RequestPredicate getPlayersRoute =
        RequestPredicates.GET("api/v1/players")
            .and(RequestPredicates.accept(MediaType.APPLICATION_JSON));

    return RouterFunctions.route(getPlayersRoute, playerHandler::getAllPlayers);
  }

  @Bean
  @GetPlayerByIdApiInfo
  public RouterFunction<ServerResponse> getPlayerByIdRouterFunction(PlayerHandler playerHandler) {
    RequestPredicate getPlayersRoute =
        RequestPredicates.GET("api/v1/players/{id}")
            .and(RequestPredicates.accept(MediaType.APPLICATION_JSON));

    return RouterFunctions.route(getPlayersRoute, playerHandler::getPlayerById);
  }

  @Bean
  @AddPlayerApiInfo
  public RouterFunction<ServerResponse> addPlayerRouterFunction(PlayerHandler playerHandler) {
    RequestPredicate getPlayersRoute =
        RequestPredicates.POST("api/v1/players")
            .and(RequestPredicates.accept(MediaType.APPLICATION_JSON));

    return RouterFunctions.route(getPlayersRoute, playerHandler::savePlayer);
  }

  @Bean
  @DeletePlayerByIdApiInfo
  public RouterFunction<ServerResponse> deletePlayerRouterFunction(PlayerHandler playerHandler) {
    RequestPredicate getPlayersRoute =
        RequestPredicates.DELETE("api/v1/players/{id}")
            .and(RequestPredicates.accept(MediaType.APPLICATION_JSON));

    return RouterFunctions.route(getPlayersRoute, playerHandler::deletePlayerById);
  }
}
