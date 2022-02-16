import { PostLineStep, step } from "../../testPost";
import { getByRole, screen } from "@testing-library/dom";

function dismissMessage() {
  const messageElement = screen.getByRole("alert");
  const dismissButton = getByRole(messageElement, "button", {
    name: "dismiss",
  });
  dismissButton.click();
}

export const messageTestSteps: PostLineStep[] = [
  step(/You should see a message saying that "([^"]+)"/, (line, match) => {
    const text = match[1];
    const messageElement = screen.getByRole("alert");
    expect(messageElement).toHaveTextContent(text);
    dismissMessage();
  }),
  step(
    /You should see an error message saying that "([^"]+)"/,
    (line, match) => {
      const text = match[1];
      const messageElement = screen.getByRole("alert");
      expect(messageElement).toHaveTextContent("⚠");
      expect(messageElement).toHaveTextContent(text);
      dismissMessage();
    }
  ),
];
