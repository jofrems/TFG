import { Middleware } from "redux";
import { AppState } from "www/store";
import { fetchAndDispatchMessage } from "www/widgets/MessageWidget/fetchAndDispatchMessage";
import {
  loadingFinished,
  loadingStarted,
} from "www/widgets/LoadingWidget/actions";
import { screenPushed } from "www/widgets/ScreenStackWidget/actions";
import {
  GameReplacedAction,
  GAME_CREATED,
  GAME_JOINED,
  GAME_PLAYED,
  GAME_REFRESHED,
  GAME_REPLACED,
  ROUND_ENDED,
} from "./types";
import { getPlayerToken } from "../player/selectors";
import { gameReplaced } from "./actions";
import { playerReplaced } from "../player/actions";
import { getGame } from "./selectors";
import { gamePost } from "./gamePost";

export const gameMiddleware: Middleware<{}, AppState> =
  (store: any) => (next) => async (action) => {
    next(action);

    if (action.type === GAME_CREATED) {
      await createGame(store, action);
    }
    if (action.type === GAME_JOINED) {
      await joinGame(store, action);
    }
    if (action.type === GAME_PLAYED) {
      await playGame(store, action);
    }
    if (action.type === GAME_REFRESHED) {
      await refreshGame(store, action);
    }
    if (action.type === GAME_REPLACED) {
      await replacePlayer(store, action);
    }
    if (action.type === ROUND_ENDED) {
      await endRound(store);
    }
  };

async function createGame(store: any, action: any) {
  store.dispatch(loadingStarted());
  const token = getPlayerToken(store.getState());
  const result = await fetchAndDispatchMessage(
    "/api/v1/games",
    { method: "POST", body: { ...action.form, token } },
    store.dispatch
  );
  if (!result.isError) store.dispatch(screenPushed("player"));
  store.dispatch(loadingFinished());
}

async function joinGame(store: any, action: any) {
  store.dispatch(loadingStarted());
  const token = getPlayerToken(store.getState());
  const result = await fetchAndDispatchMessage(
    `/api/v1/games/join`,
    { method: "POST", body: { ...action.form, token } },
    store.dispatch
  );
  if (!result.isError) store.dispatch(screenPushed("player"));
  store.dispatch(loadingFinished());
}

async function playGame(store: any, action: any) {
  store.dispatch(loadingStarted());
  const token = getPlayerToken(store.getState());
  const result = await fetchAndDispatchMessage(
    `/api/v1/games/${action.form.gameName}/by/${action.form.creatorName}?token=${token}`,
    { method: "GET" },
    store.dispatch
  );
  if (!result.isError) {
    store.dispatch(gameReplaced(result));
    store.dispatch(screenPushed("game"));
  }
  store.dispatch(loadingFinished());
}

async function refreshGame(store: any, action: any) {
  const { gameName, creatorName } = getGame(store.getState())!;
  store.dispatch(loadingStarted());
  const token = getPlayerToken(store.getState());
  const result = await fetchAndDispatchMessage(
    `/api/v1/games/${gameName}/by/${creatorName}?token=${token}`,
    { method: "GET" },
    store.dispatch
  );
  store.dispatch(gameReplaced(result));
  store.dispatch(loadingFinished());
}

async function replacePlayer(store: any, action: any) {
  const { game } = action as GameReplacedAction;
  store.dispatch(playerReplaced(game!.playerName, game!.token));
}

async function endRound(store: any) {
  const { gameName, creatorName } = getGame(store.getState())!;
  await gamePost(
    store,
    `/api/v1/games/${gameName}/by/${creatorName}/endRound`,
    {},
    () => store.dispatch(screenPushed("game"))
  );
}
