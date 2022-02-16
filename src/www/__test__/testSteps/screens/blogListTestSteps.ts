import { PostLineStep, step } from "../../testPost";
import { screen } from "@testing-library/dom";
import userEvent from "@testing-library/user-event";

export const blogListTestSteps: PostLineStep[] = [
  step(/Go to the Blog/, () => {
    const link = screen.getByRole("link", { name: /Blog/ });
    userEvent.click(link);
  }),
  step(/You should be at the blog screen/, () => {
    expect(screen.getByRole("heading", { name: /Blog!/ })).toBeInTheDocument();
  }),
  step(/You should see at least (\d+) posts/, (line, match) => {
    const count = +match[1];

    expect(getAllBlogListItems().length).toBeGreaterThanOrEqual(count);
  }),
  step(/You should see the post "([^"]+)"/, (line, match) => {
    const title = match[1];

    expect(screen.getByText(title)).toBeInTheDocument();
  }),
  step(/The post "([^"]+)" should be the last post/, (line, match) => {
    const title = match[1];

    const allItems = getAllBlogListItems();
    const lastItem = allItems[allItems.length - 1];
    expect(lastItem).toHaveTextContent(title);
  }),
  step(/Click on the post "([^"]+)"/, (line, match) => {
    const title = match[1];

    const allItems = getAllBlogListItems();
    const theItem = allItems.find((item) => item.textContent?.includes(title));
    userEvent.click(theItem!);
  }),
];

function getAllBlogListItems(): HTMLElement[] {
  return screen.getAllByRole("listitem");
}
