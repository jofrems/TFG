import { BlogActionTypes, BlogState, BLOG_REPLACED } from "./types";

const initialState: BlogState = [];

export function screenBlogReducer(
  state = initialState,
  action: BlogActionTypes
) {
  switch (action.type) {
    case BLOG_REPLACED:
      return action.blog;
    default:
      return state;
  }
}
