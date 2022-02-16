import {
  NextPlayerAddedAction,
  NextPlayerChangedAction,
  NextPlayerPushedAction,
  NEXT_PLAYER_ADDED,
  NEXT_PLAYER_CHANGED,
  NEXT_PLAYER_PUSHED,
} from "./types";

export function nextPlayerAdded(
  playerName: string,
  password: string
): NextPlayerAddedAction {
  return {
    type: NEXT_PLAYER_ADDED,
    form: {
      playerName,
      password,
    },
  };
}

export function nextPlayerChanged(): NextPlayerChangedAction {
  return {
    type: NEXT_PLAYER_CHANGED,
  };
}

export function nextPlayerPushed(
  playerName: string,
  token: string
): NextPlayerPushedAction {
  return {
    type: NEXT_PLAYER_PUSHED,
    player: {
      playerName,
      token,
    },
  };
}
