import { Middleware } from "redux";
import { AppState } from "www/store";
import { gamePost } from "www/store/game/gamePost";
import { ComponentNamedActionTypes, NAME_CHANGED } from "./types";

export const componentNamedMiddleware: Middleware<{}, AppState> =
  (store: any) => (next) => async (action: ComponentNamedActionTypes) => {
    next(action);

    if (action.type === NAME_CHANGED) {
      await gamePost(
        store,
        `/api/v1/nameds/${action.entityId}/name`,
        action.form
      );
    }
  };
