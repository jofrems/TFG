import { fetchAndDispatchMessage } from "www/widgets/MessageWidget/fetchAndDispatchMessage";
import { Middleware } from "redux";
import { AppState, AppStore } from "www/store";
import { LOGGED_IN, SIGNED_UP } from "./types";
import {
  loadingFinished,
  loadingStarted,
} from "www/widgets/LoadingWidget/actions";
import { screenPushed } from "www/widgets/ScreenStackWidget/actions";
import { playerReplaced } from "./actions";

export const playerMiddleware: Middleware<{}, AppState> =
  (store: any) => (next) => async (action) => {
    next(action);

    if (action.type === LOGGED_IN) {
      await login(store, action);
    }
    if (action.type === SIGNED_UP) {
      await signup(store, action);
    }
  };

async function login(store: AppStore, action: any) {
  store.dispatch(loadingStarted());
  var result = await fetchAndDispatchMessage(
    "/api/v1/players/login",
    { method: "POST", body: action.form },
    store.dispatch
  );
  if (!result.isError) {
    store.dispatch(screenPushed("player"));
    store.dispatch(playerReplaced(result.playerName, result.token));
  }
  store.dispatch(loadingFinished());
}

async function signup(store: any, action: any) {
  store.dispatch(loadingStarted());
  var result = await fetchAndDispatchMessage(
    "/api/v1/players",
    { method: "POST", body: action.form },
    store.dispatch
  );
  if (!result.isError) store.dispatch(screenPushed("welcome"));
  store.dispatch(loadingFinished());
}
