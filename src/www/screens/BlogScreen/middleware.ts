import { Middleware } from "redux";
import { AppState } from "www/store";
import {
  loadingFinished,
  loadingStarted,
} from "www/widgets/LoadingWidget/actions";
import { screenPushed } from "www/widgets/ScreenStackWidget/actions";
import { blogReplaced } from "./actions";
import { BLOG_PUSHED } from "./types";

export const blogMiddleware: Middleware<{}, AppState> =
  (store: any) => (next) => async (action) => {
    next(action);

    if (action.type === BLOG_PUSHED) await blogPushed(store);
  };

async function blogPushed(store: any) {
  store.dispatch(loadingStarted());

  const response = await fetch("/api/v1/posts");
  const blog = await response.json();
  store.dispatch(blogReplaced(blog.posts));
  store.dispatch(screenPushed("blog"));

  store.dispatch(loadingFinished());
}
