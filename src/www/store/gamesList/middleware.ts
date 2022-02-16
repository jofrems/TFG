import { Middleware } from "redux";
import { AppState } from "www/store";
import { fetchAndDispatchMessage } from "www/widgets/MessageWidget/fetchAndDispatchMessage";
import {
  loadingFinished,
  loadingStarted,
} from "www/widgets/LoadingWidget/actions";
import { screenPushed } from "www/widgets/ScreenStackWidget/actions";
import {
  GAMES_BY_CREATOR_LISTED,
  GAMES_BY_PLAYER_LISTED,
  MY_CREATED_GAMES_LISTED,
  MY_PLAYING_GAMES_LISTED,
} from "./types";
import { getPlayerName, getPlayerToken } from "../player/selectors";
import {
  gamesByCreatorListed,
  gamesByPlayerListed,
  gamesListReplaced,
} from "./actions";

export const gamesListMiddleware: Middleware<{}, AppState> =
  (store: any) => (next) => async (action) => {
    next(action);

    if (action.type === GAMES_BY_CREATOR_LISTED) {
      await listByCreator(store, action);
    }
    if (action.type === GAMES_BY_PLAYER_LISTED) {
      await listByPlayer(store, action);
    }
    if (action.type === MY_CREATED_GAMES_LISTED) {
      await listByCurrentPlayer(store);
    }
    if (action.type === MY_PLAYING_GAMES_LISTED) {
      await listByCurrentPlayingPlayer(store);
    }
  };

async function listByCurrentPlayer(store: any) {
  const playerName = getPlayerName(store.getState())!;
  store.dispatch(gamesByCreatorListed(playerName));
}

async function listByCurrentPlayingPlayer(store: any) {
  store.dispatch(gamesByPlayerListed());
}

async function listByCreator(store: any, action: any) {
  store.dispatch(loadingStarted());
  const result = await fetchAndDispatchMessage(
    "/api/v1/games/byPlayer",
    { method: "POST", body: action.form },
    store.dispatch
  );
  if (!result.isError) {
    store.dispatch(gamesListReplaced(result.games));
    store.dispatch(screenPushed("gamesList"));
  }
  store.dispatch(loadingFinished());
}

async function listByPlayer(store: any, action: any) {
  store.dispatch(loadingStarted());
  const token = getPlayerToken(store.getState());
  const result = await fetchAndDispatchMessage(
    "/api/v1/games/byJoined",
    { method: "POST", body: { token } as any },
    store.dispatch
  );
  store.dispatch(gamesListReplaced(result.games));
  store.dispatch(screenPushed("gamesList"));
  store.dispatch(loadingFinished());
}
