import { PostLineStep, step } from "../../testPost";
import { screen } from "@testing-library/dom";

export const elementsTestSteps: PostLineStep[] = [
  step(/There should be (\d+) ([a-z ]+) in the inventory/, (line, [, quantity, element]) => {
  console.log(element);
    const main = screen.getByRole("main");
    expect(main).toHaveTextContent(
      new RegExp(`${element}[: ]+${quantity}`, "i")
    );
  }),
];
