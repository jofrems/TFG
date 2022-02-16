import { PostLineStep, step } from "../../testPost";
import { screen } from "@testing-library/dom";
import userEvent from "@testing-library/user-event";

export const createGameTestSteps: PostLineStep[] = [
  step(/Go to create game/, () => {
    const link = screen.getByRole("link", { name: "Create game" });
    userEvent.click(link);
  }),
  step(/You should be at the create game screen/, () => {
    expect(
      screen.getByRole("heading", { name: "Create game!" })
    ).toBeInTheDocument();
  }),
  step(/Click the create game button/, () => {
    const button = screen.getByRole("button", { name: "Create game" });
    userEvent.click(button);
  }),
  step(/Add "([^"]+)" as game name/, (line, [, gameName]) => {
    var input = screen.getByLabelText("Game name:") as HTMLInputElement;
    userEvent.clear(input);
    userEvent.type(input, gameName);
  }),
  step(/Cancel (the )?create game/, () => {
    const button = screen.getByRole("button", { name: "Cancel" });
    userEvent.click(button);
  }),
];
