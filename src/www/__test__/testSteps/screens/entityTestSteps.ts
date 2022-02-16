import { PostLineStep, step } from "../../testPost";
import { screen } from "@testing-library/dom";
import userEvent from "@testing-library/user-event";

export const entityTestSteps: PostLineStep[] = [
  step(/You should be at the screen of a ([a-z]+)/, (line, [, type]) => {
    expect(
      screen.getByRole("heading", { name: new RegExp(type, "i") })
    ).toBeInTheDocument();
  }),
  step(/The name should be "([^"]+)"/, (line, [, name]) => {
    expect(
      screen.getByRole("heading", { name: new RegExp(name, "i") })
    ).toBeInTheDocument();
  }),
  step(/The owner should be "([^"]+)"/, (line, [, owner]) => {
    expect(
      screen.getByRole("heading", { name: new RegExp(owner, "i") })
    ).toBeInTheDocument();
  }),
  step(/Go back to the previous screen/, (line, [, name]) => {
    const button = screen.getByRole("link", { name: "« Back" });
    userEvent.click(button);
  }),
  step(/There should not be a back button/, (line, [, name]) => {
    const button = screen.queryByRole("link", { name: "« Back" });
    expect(button).toBeNull();
  }),
];
