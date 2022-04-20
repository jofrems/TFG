import { Middleware } from "redux";
import { AppState } from "www/store";
import { gamePost } from "www/store/game/gamePost";
import { ComponentDiceActionTypes, ROLL } from "./types";

export const componentDiceMiddleware: Middleware<{}, AppState> =
  (store: any) => (next) => async (action: ComponentDiceActionTypes) => {
    next(action);
    console.log("reach middleware + action: "+action.type);
    if (action.type === ROLL) {
      await gamePost(
        store,
        `/api/v1/dice/${action.entityId}/${action.playerName}/roll`,
        {}
      );
    }
  };
