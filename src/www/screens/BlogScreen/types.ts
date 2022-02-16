export interface BlogEntry {
  id: string;
  title: string;
  frontMatter: {
    coder: string;
    writer: string;
  };
}

export type BlogState = BlogEntry[];

export const BLOG_PUSHED = "screenBlog/BLOG_PUSHED";
export interface BlogPushedAction {
  type: typeof BLOG_PUSHED;
}

export const BLOG_REPLACED = "screenBlog/BLOG_REPLACED";
export interface BlogReplacedAction {
  type: typeof BLOG_REPLACED;
  blog: BlogState;
}

export type BlogActionTypes = BlogPushedAction | BlogReplacedAction;
