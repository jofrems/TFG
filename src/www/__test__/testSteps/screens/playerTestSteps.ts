import { PostLineStep, step } from "../../testPost";
import { screen } from "@testing-library/dom";
import userEvent from "@testing-library/user-event";

export const playerTestSteps: PostLineStep[] = [
  step(/You should be (back )?at the player screen/, () => {
    expect(
      screen.getByRole("heading", { name: /Welcome.*player!/ })
    ).toBeInTheDocument();
  }),
  step(/"([^"]+)" should be the current player/, (title, [, playerName]) => {
    expect(screen.getByTestId("player-header")).toHaveTextContent(playerName);
  }),
  step(/Enter "([^"]*)" as friend name/, (line, [, text]) => {
    var input = screen.getByLabelText("Friend name:");
    userEvent.clear(input);
    userEvent.type(input, text);
  }),
  step(/Go to friend games/, () => {
    const button = screen.getByRole("button", { name: "Friend games" });
    userEvent.click(button);
  }),
  step(/Go to my playing games/, () => {
    const link = screen.getByRole("link", {
      name: /My playing games/,
    });
    userEvent.click(link);
  }),
];
