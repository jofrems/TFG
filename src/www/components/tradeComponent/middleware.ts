import { Middleware } from "redux";
import { AppState } from "www/store";
import { gamePost } from "www/store/game/gamePost";
import { ComponentTradeActionTypes, TRADE, TradeAction } from "./types";

export const componentTradeMiddleware: Middleware<{}, AppState> =
  (store: any) => (next) => async (action: ComponentTradeActionTypes) => {
    next(action);
    //console.log("form: "+action.form.getResources);
    if (action.type === TRADE) {
      await orderTrade(store, action, "trade");
    }
  };

  async function orderTrade(
    store: any,
    action: TradeAction,
    order: string
  ) {
    await gamePost(
      store,
      `/api/v1/resources/${action.entityId}/${order}`,
      action.form
    );
  }
