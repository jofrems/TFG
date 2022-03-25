import { Middleware } from "redux";
import { AppState } from "www/store";
import { gamePost } from "www/store/game/gamePost";
import { ComponentOwnActionTypes, OWN } from "./types";

export const componentOwnMiddleware: Middleware<{}, AppState> =
  (store: any) => (next) => async (action: ComponentOwnActionTypes) => {
    next(action);
    console.log("reach middleware + action: "+action.type);
    if (action.type === OWN) {
      await gamePost(
        store,
        `/api/v1/owned/${action.entityId}/${action.playerName}/own`,
        {}
      );
    }
  };
