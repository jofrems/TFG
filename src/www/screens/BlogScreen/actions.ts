import {
  BlogPushedAction,
  BlogReplacedAction,
  BlogState,
  BLOG_PUSHED,
  BLOG_REPLACED,
} from "./types";

export function blogPushed(): BlogPushedAction {
  return {
    type: BLOG_PUSHED,
  };
}

export function blogReplaced(blog: BlogState): BlogReplacedAction {
  return {
    type: BLOG_REPLACED,
    blog,
  };
}
