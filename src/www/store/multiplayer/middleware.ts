import { Middleware } from "redux";
import { AppState } from "www/store";
import { fetchAndDispatchMessage } from "www/widgets/MessageWidget/fetchAndDispatchMessage";
import {
  loadingFinished,
  loadingStarted,
} from "www/widgets/LoadingWidget/actions";
import { screenPushed } from "www/widgets/ScreenStackWidget/actions";
import {
  NextPlayerAddedAction,
  NextPlayerChangedAction,
  NEXT_PLAYER_ADDED,
  NEXT_PLAYER_CHANGED,
} from "./types";
import { getPlayerToken } from "../player/selectors";
import { getGame } from "../game/selectors";
import { gamePlayed, gameReplaced, gameRefreshed } from "../game/actions";
import { nextPlayerPushed } from "./actions";
import { getMultiplayerCurrentPlayer } from "./selectors";
import { playerReplaced } from "../player/actions";

export const multiplayerMiddleware: Middleware<{}, AppState> =
  (store: any) => (next) => async (action) => {
    next(action);

    if (action.type === NEXT_PLAYER_ADDED) {
      await addNextPlayer(store, action);
    }
    if (action.type === NEXT_PLAYER_CHANGED) {
      await changeNextPlayer(store, action);
    }
  };

async function addNextPlayer(store: any, action: NextPlayerAddedAction) {
  store.dispatch(loadingStarted());
  const state = store.getState();
  const token = getPlayerToken(state)!;
  const { gameName, creatorName } = getGame(state)!;

  var result = await fetchAndDispatchMessage(
    `/api/v1/games/joinNext?token=${token}`,
    { method: "POST", body: { ...action.form, gameName, creatorName } as any },
    store.dispatch
  );
  if (!result.isError) {
    store.dispatch(nextPlayerPushed(creatorName, token));
    store.dispatch(nextPlayerPushed(result.playerName, result.token));
    store.dispatch(gameReplaced(result));
    store.dispatch(screenPushed("game"));
  }
  store.dispatch(loadingFinished());
}

async function changeNextPlayer(store: any, action: NextPlayerChangedAction) {
  const state = store.getState();
  const next = getMultiplayerCurrentPlayer(state);
  if (!next) {
    store.dispatch(gameRefreshed());
    return;
  }

  const { playerName, token } = next;
  const { gameName, creatorName } = getGame(state)!;

  store.dispatch(playerReplaced(playerName, token));
  store.dispatch(gamePlayed(gameName, creatorName));
  // store.dispatch(screenPushed("game"));
}
