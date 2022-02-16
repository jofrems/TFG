import {
  PostPushedAction,
  PostReplacedAction,
  PostState,
  POST_PUSHED,
  POST_REPLACED,
} from "./types";

export function postPushed(postId: string): PostPushedAction {
  return {
    type: POST_PUSHED,
    postId,
  };
}

export function postReplaced(post: PostState): PostReplacedAction {
  return {
    type: POST_REPLACED,
    post,
  };
}
