import { PostLineStep, step } from "../../testPost";
import { screen } from "@testing-library/dom";
import userEvent from "@testing-library/user-event";

export const welcomeTestSteps: PostLineStep[] = [
  step(/You should be (back )?at the welcome screen/, () => {
    expect(
      screen.getByRole("heading", { name: "Welcome to the game!" })
    ).toBeInTheDocument();
  }),
  step(/Go to the Welcome screen/, () => {
    const link = screen.getByRole("link", { name: /^Welcome$/ });
    userEvent.click(link);
  }),
];
