import { Middleware } from "redux";
import { AppState } from "www/store";
import { gamePost } from "www/store/game/gamePost";
import {
  ComponentLoadableActionTypes,
  LoadOrderedAction,
  LOAD_ORDERED,
  UnloadOrderedAction,
  UNLOAD_ORDERED,
} from "./types";

export const componentLoadableMiddleware: Middleware<{}, AppState> =
  (store: any) => (next) => async (action: ComponentLoadableActionTypes) => {
    next(action);

    if (action.type === LOAD_ORDERED)
      await orderLoadable(store, action, "load");

    if (action.type === UNLOAD_ORDERED)
      await orderLoadable(store, action, "unload");
  };

async function orderLoadable(
  store: any,
  action: LoadOrderedAction | UnloadOrderedAction,
  order: string
) {
  await gamePost(
    store,
    `/api/v1/loadables/${action.entityId}/${order}`,
    action.form
  );
}
