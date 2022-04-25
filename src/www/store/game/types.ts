type EntityState = {
  id: string;
  [key: string]: any;
};

export type GameState = null | {
  gameName: string;
  creatorName: string;
  roundNumber: number;
  playerName: string;
  playerNameTurn: string;
  token: string;
  entities: {
    [id: string]: EntityState;
  };
};

export const GAME_CREATED = "game/CREATED";
export interface GameCreatedAction {
  type: typeof GAME_CREATED;
  form: {
    gameName: string;
  };
}

export const GAME_JOINED = "game/JOINED";
export interface GameJoinedAction {
  type: typeof GAME_JOINED;
  form: {
    gameName: string;
    creatorName: string;
  };
}

export const GAME_PLAYED = "game/PLAYED";
export interface GamePlayedAction {
  type: typeof GAME_PLAYED;
  form: {
    gameName: string;
    creatorName: string;
  };
}

export const GAME_REFRESHED = "game/REFRESHED";
export interface GameRefreshedAction {
  type: typeof GAME_REFRESHED;
}

export const GAME_REPLACED = "game/REPLACED";
export interface GameReplacedAction {
  type: typeof GAME_REPLACED;
  game: GameState;
}

export const ROUND_ENDED = "game/ROUND_ENDED";
export interface RoundEndedAction {
  type: typeof ROUND_ENDED;
}

export type GameActionTypes =
  | GameCreatedAction
  | GameJoinedAction
  | GamePlayedAction
  | GameRefreshedAction
  | GameReplacedAction
  | RoundEndedAction;
