import {
  LoggedInAction,
  LOGGED_IN,
  PlayerReplacedAction,
  PLAYER_REPLACED,
  SignedUpAction,
  SIGNED_UP,
} from "./types";

export function loggedIn(playerName: string, password: string): LoggedInAction {
  return {
    type: LOGGED_IN,
    form: {
      playerName,
      password,
    },
  };
}

export function playerReplaced(
  playerName: string,
  token: string
): PlayerReplacedAction {
  return {
    type: PLAYER_REPLACED,
    player: {
      playerName,
      token,
    },
  };
}

export function signedUp(playerName: string, password: string): SignedUpAction {
  return {
    type: SIGNED_UP,
    form: {
      playerName,
      password,
    },
  };
}
