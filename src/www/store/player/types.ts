export type PlayerState = null | {
  playerName: string;
  token: string;
};

export const LOGGED_IN = "player/LOGGED_IN";
export interface LoggedInAction {
  type: typeof LOGGED_IN;
  form: {
    playerName: string;
    password: string;
  };
}

export const PLAYER_REPLACED = "player/REPLACED";
export interface PlayerReplacedAction {
  type: typeof PLAYER_REPLACED;
  player: {
    playerName: string;
    token: string;
  };
}

export const SIGNED_UP = "player/SIGNED_UP";
export interface SignedUpAction {
  type: typeof SIGNED_UP;
  form: {
    playerName: string;
    password: string;
  };
}

export type PlayerActionTypes =
  | LoggedInAction
  | PlayerReplacedAction
  | SignedUpAction;
