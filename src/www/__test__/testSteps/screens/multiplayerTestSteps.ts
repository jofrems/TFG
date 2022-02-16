import { PostLineStep, step } from "../../testPost";
import { testDispatch } from "../../testStore";
import { screen } from "@testing-library/dom";
import userEvent from "@testing-library/user-event";
import { nextPlayerAdded } from "www/store/multiplayer/actions";

export const multiplayerTestSteps: PostLineStep[] = [
  step(/there is the next player "([^"]+)"/, (title, [, playerName]) => {
    testDispatch(nextPlayerAdded(playerName, "tbbt12"));
  }),
  step(/Go to Multiplayer/, () => {
    const button = screen.getByRole("link", { name: "Multiplayer" });
    userEvent.click(button);
  }),
  step(/You should be at the add next player screen/, () => {
    expect(
      screen.getByRole("heading", { name: "Add next player!" })
    ).toBeInTheDocument();
  }),
  step(/Enter "([^"]+)" as next player name/, (line, [, playerName]) => {
    enterNextPlayerName(playerName);
  }),
  step(/Enter "([^"]+)" as next player password/, (line, [, password]) => {
    enterNextPlayerPassword(password);
  }),
  step(/Click the Join Next button/, () => {
    clickTheJoinNextButton();
  }),
  step(/Go to Next player/, () => {
    const button = screen.getByRole("link", { name: "Next" });
    userEvent.click(button);
  }),
  step(/Cancel add next player/, () => {
    const button = screen.getByRole("button", { name: "Cancel" });
    userEvent.click(button);
  }),
];

function enterNextPlayerName(playerName: string) {
  var input = screen.getByLabelText("Next player name:");
  userEvent.clear(input);
  userEvent.type(input, playerName);
}

function enterNextPlayerPassword(password: string) {
  var input = screen.getByLabelText("Next player password:");
  userEvent.clear(input);
  userEvent.type(input, password);
}

function clickTheJoinNextButton() {
  const button = screen.getByRole("button", { name: "Join Next" });
  userEvent.click(button);
}
