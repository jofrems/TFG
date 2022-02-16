import { AppState } from "www/store";

export function getBlog(state: AppState) {
  return state.screenBlog;
}
