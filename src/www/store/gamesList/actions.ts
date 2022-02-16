import {
  MY_CREATED_GAMES_LISTED,
  MyCreatedGamesListedAction,
  GamesByCreatorListedAction,
  GAMES_BY_CREATOR_LISTED,
  GamesList,
  GamesListReplacedAction,
  GAMES_LIST_REPLACED,
  MyPlayingGamesListedAction,
  MY_PLAYING_GAMES_LISTED,
  GamesByPlayerListedAction,
  GAMES_BY_PLAYER_LISTED,
} from "./types";

export function gamesByCreatorListed(
  playerName: string
): GamesByCreatorListedAction {
  return {
    type: GAMES_BY_CREATOR_LISTED,
    form: { playerName },
  };
}

export function gamesByPlayerListed(): GamesByPlayerListedAction {
  return {
    type: GAMES_BY_PLAYER_LISTED,
  };
}

export function myCreatedGamesListed(): MyCreatedGamesListedAction {
  return {
    type: MY_CREATED_GAMES_LISTED,
  };
}

export function myPlayingGamesListed(): MyPlayingGamesListedAction {
  return {
    type: MY_PLAYING_GAMES_LISTED,
  };
}

export function gamesListReplaced(
  gamesList: GamesList
): GamesListReplacedAction {
  return {
    type: GAMES_LIST_REPLACED,
    gamesList,
  };
}
