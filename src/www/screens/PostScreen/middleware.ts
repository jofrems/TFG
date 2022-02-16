import { Middleware } from "redux";
import { AppState } from "www/store";
import {
  loadingFinished,
  loadingStarted,
} from "www/widgets/LoadingWidget/actions";
import { screenPushed } from "www/widgets/ScreenStackWidget/actions";
import { postReplaced } from "./actions";
import { PostPushedAction, POST_PUSHED } from "./types";

export const postMiddleware: Middleware<{}, AppState> =
  (store: any) => (next) => async (action) => {
    next(action);

    if (action.type === POST_PUSHED) await postPushed(store, action);
  };

async function postPushed(store: any, action: PostPushedAction) {
  store.dispatch(loadingStarted());

  const response = await fetch("/api/v1/posts/" + action.postId);
  const post = await response.json();
  store.dispatch(postReplaced(post));
  store.dispatch(screenPushed("post"));

  store.dispatch(loadingFinished());
}
