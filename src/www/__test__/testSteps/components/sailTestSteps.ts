import { PostLineStep, step } from "../../testPost";
import { screen } from "@testing-library/dom";
import userEvent from "@testing-library/user-event";

export const sailTestSteps: PostLineStep[] = [
  step(/The destination location should be (\d+)/, (line, [, location]) => {
    const main = screen.getByRole("main");
    expect(main).toHaveTextContent(
      new RegExp(`Destination location[: ]+${location}[^\\d]`, "i")
    );
  }),
  step(/The destination sail should be active/, () => {
    const main = screen.getByRole("main");
    expect(main).toHaveTextContent(
      new RegExp(`Destination location.*(sailing)`, "i")
    );
  }),
  step(/The destination sail should be inactive/, () => {
    const main = screen.getByRole("main");
    expect(main).not.toHaveTextContent(
      new RegExp(`Destination location.*(sailing)`, "i")
    );
  }),
  step(/Enter destination location as (\d+)/, (line, [, location]) => {
    const input = screen.getByLabelText("Destination location:");
    userEvent.type(input, location);
  }),
  step(/Click the destination sail button/, () => {
    const button = screen.getByRole("button", { name: "Sail" });
    userEvent.click(button);
  }),
  step(/Click the destination halt button/, () => {
    const button = screen.getByRole("button", { name: "Halt" });
    userEvent.click(button);
  }),
];
