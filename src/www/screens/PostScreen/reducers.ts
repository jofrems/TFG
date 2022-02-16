import { PostActionTypes, PostState, POST_REPLACED } from "./types";

const initialState: PostState = null;

export function screenPostReducer(
  state = initialState,
  action: PostActionTypes
) {
  switch (action.type) {
    case POST_REPLACED:
      return action.post;
    default:
      return state;
  }
}
