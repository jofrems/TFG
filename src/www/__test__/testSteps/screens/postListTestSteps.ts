import { PostLineStep, step } from "../../testPost";
import { screen } from "@testing-library/dom";
import userEvent from "@testing-library/user-event";
import { getByRole } from "@testing-library/react";

export const postListTestSteps: PostLineStep[] = [
  step(/You should see the post title "([^"]+)"/, (line, [, title]) => {
    expect(
      screen.getByRole("heading", { name: `${title}!` })
    ).toBeInTheDocument();
  }),
  step(/The post should contain the text "([^"]+)"/, (line, [, text]) => {
    const main = screen.getByRole("main");
    expect(main).toHaveTextContent(text);
  }),
  step(/Click in the go back to the blog/, (line, match) => {
    const link = screen.getByRole("link", { name: `« Back to blog` });
    userEvent.click(link);
  }),
];
