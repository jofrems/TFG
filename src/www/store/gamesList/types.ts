export type GamesListItem = {
  gameName: string;
  creatorName: string;
  joinedPlayerNames: string[];
};

export type GamesList = GamesListItem[];

export type GamesListState = GamesList;

export const GAMES_BY_CREATOR_LISTED = "gamesList/GAMES_BY_CREATOR_LISTED";
export interface GamesByCreatorListedAction {
  type: typeof GAMES_BY_CREATOR_LISTED;
  form: { playerName: string };
}

export const GAMES_BY_PLAYER_LISTED = "gamesList/GAMES_BY_PLAYER_LISTED";
export interface GamesByPlayerListedAction {
  type: typeof GAMES_BY_PLAYER_LISTED;
}

export const GAMES_LIST_REPLACED = "gamesList/GAMES_LIST_REPLACED";
export interface GamesListReplacedAction {
  type: typeof GAMES_LIST_REPLACED;
  gamesList: GamesList;
}

export const MY_CREATED_GAMES_LISTED = "gamesList/MY_CREATED_GAMES_LISTED";
export interface MyCreatedGamesListedAction {
  type: typeof MY_CREATED_GAMES_LISTED;
}

export const MY_PLAYING_GAMES_LISTED = "gamesList/MY_PLAYING_GAMES_LISTED";
export interface MyPlayingGamesListedAction {
  type: typeof MY_PLAYING_GAMES_LISTED;
}

export type GamesListActionTypes =
  | GamesByCreatorListedAction
  | GamesByPlayerListedAction
  | GamesListReplacedAction
  | MyCreatedGamesListedAction
  | MyPlayingGamesListedAction;
