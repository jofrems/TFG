import { useAppSelector } from "www/store/hooks";
import { getBlog } from "./selectors";
import { BlogItem } from "./BlogItem";

export function BlogScreen() {
  const blog = useAppSelector(getBlog);

  return (
    <main>
      <h1>
        Blog! <small style={{ fontSize: "0.7rem" }}>(#{blog.length})</small>
      </h1>
      <ul>
        {blog.map((post) => (
          <BlogItem key={post.id} post={post} />
        ))}
      </ul>
    </main>
  );
}
