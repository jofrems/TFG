import { PostLineStep, step } from "../../testPost";
import { screen } from "@testing-library/dom";
import userEvent from "@testing-library/user-event";

export const loginTestSteps: PostLineStep[] = [
  step(/Go to the login/, () => {
    goToTheLogin();
  }),
  step(/You should be at the login screen/, () => {
    expect(
      screen.getByRole("heading", { name: "Log In!" })
    ).toBeInTheDocument();
  }),
  step(/Enter your player name/, () => {
    enterPlayerName("leonard");
  }),
  step(/Enter "([^"]+)" as player name/, (line, [, playerName]) => {
    enterPlayerName(playerName);
  }),
  step(/Enter your password/, () => {
    enterPassword("tbbt12");
  }),
  step(/Enter "([^"]+)" as password/, (line, [, password]) => {
    enterPassword(password);
  }),
  step(/Click the login button/, () => {
    clickTheLoginButton();
  }),
  step(/you have been logged in/, () => {
    hasBeenLoggedIn("leonard");
  }),
  step(/"([^"]+)" has been logged in/, (line, [, playerName]) => {
    hasBeenLoggedIn(playerName);
  }),
];

function goToTheLogin() {
  const button = screen.getByRole("link", { name: "Login" });
  userEvent.click(button);
}

function enterPlayerName(playerName: string) {
  var input = screen.getByLabelText("Player name:") as HTMLInputElement;
  userEvent.clear(input);
  userEvent.type(input, playerName);
}

function enterPassword(password: string) {
  var input = screen.getByLabelText("Password:");
  userEvent.clear(input);
  userEvent.type(input, password);
}

function clickTheLoginButton() {
  const button = screen.getByRole("button", { name: "Login" });
  userEvent.click(button);
}

function hasBeenLoggedIn(playerName: string) {
  goToTheLogin();
  enterPlayerName(playerName);
  enterPassword("tbbt12");
  clickTheLoginButton();
}
