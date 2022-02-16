import chalk from "chalk";
import { PostLine } from "./PostLine";
import { PostLineStep } from "./PostLineStep";
import { PostTest } from "./PostTest";
import { snapshotService } from "./SnapshotService";

export class PostTestRunner {
  #postLineSteps: PostLineStep[];
  #postTest: PostTest;

  constructor(postTest: PostTest, postLineSteps: PostLineStep[]) {
    this.#postTest = postTest;
    this.#postLineSteps = postLineSteps;
  }

  async run() {
    let lastLine: PostLine = null as any;
    try {
      await this.#postTest.forEachLine(async (line: PostLine) => {
        lastLine = line;
        await this.#runLine(line);
      });
    } catch (e: any) {
      this.#reportError(lastLine, e);
    }
  }

  #runLine = async (line: PostLine) => {
    var step = this.#findStep(line);
    await step.run(line);
    snapshotService.throwIfFailed();
  };

  #reportError = (lastLine: PostLine, e: any) => {
    const previousLines = this.#postTest.toPrettyStringPreviousLines(
      lastLine,
      chalk.green
    );
    const nextLines = this.#postTest.toPrettyStringNextLines(
      lastLine,
      chalk.dim,
      chalk.dim
    );
    throw new Error(
      "\nError found at: " +
        ["", ...previousLines].join(chalk.green("\n✓ ")) +
        chalk.red.bold("\n✘ ") +
        lastLine.toPrettyString(chalk.red.bold) +
        ["", ...nextLines.slice(0, 3)].join(chalk.dim("\n☐ ")) +
        (nextLines.length > 3 ? chalk.dim("\n☐ ...") : "") +
        "\n\n" +
        e.stack +
        chalk.keyword("brown")(
          "\n\nKindly reminder: the blogpost tested is the one at the 'target/' directory. This directory is generated when the backend compiles and executes all tests. So, be sure that you have compiled and tested the backend after any change in the blogposts.\n"
        )
    );
  };

  #findStep = (line: PostLine): PostLineStep => {
    var steps = this.#postLineSteps.filter((step) => step.matches(line));

    if (steps.length === 0)
      throw new Error(
        "No matching step for line: \n>> '" + line.toPrettyString() + "'"
      );

    if (steps.length > 1)
      throw new Error(
        'Multiple matching steps for line: "' +
          line.toPrettyString() +
          '"\nThey are:\n - ' +
          steps.map((step) => step.toPrettyString()).join("\n - ")
      );

    return steps[0];
  };
}
