import { getByText, queryByText, screen } from "@testing-library/dom";
import userEvent from "@testing-library/user-event";
import { PostLineStep, step } from "../../testPost";

export const dockTestSteps: PostLineStep[] = [
  step(
    /It should be docked at the "([^"]+)" ([a-z]+) "([^"]+)"/,
    (line, [, owner, type, name]) => {
      const main = screen.getByRole("main");
      const dockedAt = getByText(main, /docked at:/i);

      expect(dockedAt).toHaveTextContent(owner);
      expect(dockedAt).toHaveTextContent(type);
      expect(dockedAt).toHaveTextContent(name);
    }
  ),
  step(/Click on see the dock/, () => {
    const main = screen.getByRole("button", { name: "See the dock" });
    userEvent.click(main);
  }),
  step(/It should be not docked/, () => {
    const main = screen.getByRole("main");
    const dockedAt = queryByText(main, /docked at:/i);

    expect(dockedAt).toBeNull();
  }),
];
