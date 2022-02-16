export type PostState = null | {
  id: string;
  title: string;
  body: string;
};

export const POST_PUSHED = "screenPost/POST_PUSHED";
export interface PostPushedAction {
  type: typeof POST_PUSHED;
  postId: string;
}

export const POST_REPLACED = "screenPost/POST_REPLACED";
export interface PostReplacedAction {
  type: typeof POST_REPLACED;
  post: PostState;
}

export type PostActionTypes = PostPushedAction | PostReplacedAction;
