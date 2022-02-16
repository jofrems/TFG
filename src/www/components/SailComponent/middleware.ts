import { Middleware } from "redux";
import { AppState } from "www/store";
import { gamePost } from "www/store/game/gamePost";
import {
  ComponentSailActionTypes,
  HaltOrderedAction,
  HALT_ORDERED,
  SailOrderedAction,
  SAIL_ORDERED,
} from "./types";

export const componentSailMiddleware: Middleware<{}, AppState> =
  (store: any) => (next) => async (action: ComponentSailActionTypes) => {
    next(action);

    if (action.type === SAIL_ORDERED) await orderSail(store, action);
    if (action.type === HALT_ORDERED) await orderHalt(store, action);
  };

async function orderSail(store: any, action: SailOrderedAction) {
  await gamePost(store, `/api/v1/sails/${action.entityId}/sail`, action.form);
}

async function orderHalt(store: any, action: HaltOrderedAction) {
  await gamePost(store, `/api/v1/sails/${action.entityId}/halt`, {});
}
