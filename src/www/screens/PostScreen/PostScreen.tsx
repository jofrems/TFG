import { useAppSelector } from "www/store/hooks";
import { LinkDispatch } from "www/widgets/LinkDispatchWidget";
import { screenPopped } from "www/widgets/ScreenStackWidget/actions";
import { getPost } from "./selectors";
import ReactMarkdown from "react-markdown";

export function PostScreen() {
  const post = useAppSelector(getPost)!;
  const content = post.body
    .split("\n")
    .filter((l) => !l.includes("SNAPSHOT"))
    .join("\n");

  return (
    <>
      <div className="content">
        <LinkDispatch createAction={screenPopped}>« Back to blog</LinkDispatch>
      </div>
      <main>
        <h1>{post.title}!</h1>
        <ReactMarkdown children={content} />
      </main>
    </>
  );
}
