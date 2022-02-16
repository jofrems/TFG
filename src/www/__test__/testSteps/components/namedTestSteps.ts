import { PostLineStep, step } from "../../testPost";
import { screen } from "@testing-library/dom";
import userEvent from "@testing-library/user-event";

export const namedTestSteps: PostLineStep[] = [
  step(/Enter new name "([^"]+)"/, (line, [, text]) => {
    const input = screen.getByLabelText("New name:");
    userEvent.clear(input);
    userEvent.type(input, text);
  }),
  step(/Click the change name button/, (line, [, text]) => {
    const button = screen.getByRole("button", { name: "Change name" });
    userEvent.click(button);
  }),
  step(/There is no new city name formulary/, (line, [, text]) => {
    const button = screen.queryByRole("button", { name: "Change name" });
    expect(button).toBeNull();
  }),
];
