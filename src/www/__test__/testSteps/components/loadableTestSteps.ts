import { PostLineStep, step } from "../../testPost";
import { screen } from "@testing-library/dom";
import userEvent from "@testing-library/user-event";

export const loadableTestSteps: PostLineStep[] = [
  step(/The load unload amount should be (\d+)/, (line, [, amount]) => {
    const main = screen.getByRole("main");
    expect(main).toHaveTextContent(
      new RegExp(`Load/unload amount[: ]+${amount}[^\\d]`, "i")
    );
  }),
  step(
    /The (load|unload) requested should be (active|inactive)/,
    (line, [, action, active]) => {
      const isActive = active === "active";
      const button = screen.getByRole("button", {
        name: new RegExp(`^${action}$`, "i"),
      });
      expect(button).toMatchObject({ disabled: isActive });
    }
  ),
  step(/Enter load unload amount as (\d+)/, (line, [, amount]) => {
    const input = screen.getByLabelText("Load/unload amount:");
    userEvent.type(input, `${amount}`);
  }),
  step(/Click the (load|unload) button/, (line, [, action]) => {
    const button = screen.getByRole("button", {
      name: new RegExp(`^${action}$`, "i"),
    });
    userEvent.click(button);
  }),
];
