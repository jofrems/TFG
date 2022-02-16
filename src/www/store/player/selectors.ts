import { AppState } from "www/store";

export function getPlayer(state: AppState) {
  return state.player;
}

export function getPlayerToken(state: AppState) {
  return getPlayer(state)?.token;
}

export function getPlayerName(state: AppState) {
  return getPlayer(state)?.playerName;
}
