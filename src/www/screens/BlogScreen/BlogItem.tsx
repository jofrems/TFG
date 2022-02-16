import { useCallback } from "react";
import { useAppDispatch } from "www/store/hooks";
import { postPushed } from "www/screens/PostScreen/actions";
import { BlogEntry } from "./types";

export function BlogItem({ post }: { post: BlogEntry }) {
  const dispatch = useAppDispatch();
  const goToPost = useCallback(() => {
    dispatch(postPushed(post.id));
  }, [dispatch, post]);
  const { writer, coder } = post.frontMatter;

  return (
    <li onClick={goToPost} style={{ color: !coder ? "red" : "inherit" }}>
      <code>[{post.id.slice(0, 10)}]</code>{" "}
      <span role="link">{post.title}</span>
      <small> by {writer}</small>
      <small>, impl. {coder}</small>
    </li>
  );
}
