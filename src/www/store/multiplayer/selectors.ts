import { AppState } from "www/store";

export function getMultiplayerCurrentPlayer(state: AppState) {
  const { multiplayer } = state;
  return multiplayer.players[multiplayer.currentPlayerIndex];
}
