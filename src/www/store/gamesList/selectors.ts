import { AppState } from "www/store";
import { GamesList } from "./types";

export function getGamesList(state: AppState): GamesList {
  return state.gamesList;
}
