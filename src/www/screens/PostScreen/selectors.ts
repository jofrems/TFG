import { AppState } from "www/store";

export function getPost(state: AppState) {
  return state.screenPost;
}
