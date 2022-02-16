import { PostLineStep, step } from "../../testPost";
import { screen } from "@testing-library/dom";
import userEvent from "@testing-library/user-event";

export const signupTestSteps: PostLineStep[] = [
  step(/Go to the signup/, () => {
    const link = screen.getByRole("link", { name: "Sign up" });
    userEvent.click(link);
  }),
  step(/You should be at the signup screen/, () => {
    expect(
      screen.getByRole("heading", { name: "Sign up!" })
    ).toBeInTheDocument();
  }),
  step(/Add your name as player name/, () => {
    var input = screen.getByLabelText("Player name:");
    userEvent.clear(input);
    userEvent.type(input, "leonard");
  }),
  step(/Add "([^"]+)" as player name/, (line, [, text]) => {
    var input = screen.getByLabelText("Player name:") as HTMLInputElement;
    userEvent.clear(input);
    userEvent.type(input, text);
  }),
  step(/Set your password/, () => {
    var input = screen.getByLabelText("Password:");
    userEvent.clear(input);
    userEvent.type(input, "tbbt12");
  }),
  step(/Set "([^"]+)" as password/, (line, [, password]) => {
    var input = screen.getByLabelText("Password:");
    userEvent.clear(input);
    userEvent.type(input, password);
  }),
  step(/Click the signup button/, () => {
    const button = screen.getByRole("button", { name: "Signup" });
    userEvent.click(button);
  }),
];
