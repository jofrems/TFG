import { fetchAndDispatchMessage } from "www/widgets/MessageWidget/fetchAndDispatchMessage";
import {
  loadingFinished,
  loadingStarted,
} from "www/widgets/LoadingWidget/actions";
import { getPlayerToken } from "../player/selectors";
import { gameReplaced } from "./actions";
import { GameState } from "./types";

export async function gamePost(
  store: any,
  url: string,
  body: any,
  success: (game: GameState) => Promise<void> | void = () => {}
) {
  store.dispatch(loadingStarted());
  const token = getPlayerToken(store.getState());
  const result = await fetchAndDispatchMessage(
    `${url}?token=${token}`,
    { method: "POST", body },
    store.dispatch
  );

  store.dispatch(gameReplaced(result));
  await success(result);

  store.dispatch(loadingFinished());
}
