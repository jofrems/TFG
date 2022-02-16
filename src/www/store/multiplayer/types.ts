export type MultiplayerState = {
  currentPlayerIndex: number;
  players: { playerName: string; token: string }[];
};

export const NEXT_PLAYER_ADDED = "multiplayer/NEXT_PLAYER_ADDED";
export interface NextPlayerAddedAction {
  type: typeof NEXT_PLAYER_ADDED;
  form: {
    playerName: string;
    password: string;
  };
}

export const NEXT_PLAYER_CHANGED = "multiplayer/NEXT_PLAYER_CHANGED";
export interface NextPlayerChangedAction {
  type: typeof NEXT_PLAYER_CHANGED;
}

export const NEXT_PLAYER_PUSHED = "multiplayer/NEXT_PLAYER_PUSHED";
export interface NextPlayerPushedAction {
  type: typeof NEXT_PLAYER_PUSHED;
  player: {
    playerName: string;
    token: string;
  };
}

export type MultiplayerActionTypes =
  | NextPlayerPushedAction
  | NextPlayerChangedAction
  | NextPlayerAddedAction;
