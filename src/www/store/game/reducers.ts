import { GameActionTypes, GameState, GAME_REPLACED } from "./types";

export function gameReducer(state: GameState = null, action: GameActionTypes) {
  switch (action.type) {
    case GAME_REPLACED:
      return action.game;
    default:
      return state;
  }
}
