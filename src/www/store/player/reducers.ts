import { PlayerActionTypes, PlayerState, PLAYER_REPLACED } from "./types";

export function playerReducer(
  state: PlayerState = null,
  action: PlayerActionTypes
) {
  switch (action.type) {
    case PLAYER_REPLACED:
      return action.player;
    default:
      return state;
  }
}
