import { PostLineStep, step } from "../../testPost";
import { screen } from "@testing-library/dom";

export const populatedTestSteps: PostLineStep[] = [
  step(/The population should be (\d+)/, (line, [, population]) => {
    const main = screen.getByRole("main");
    expect(main).toHaveTextContent(
      new RegExp(`population[: ]+${population}`, "i")
    );
  }),
];
