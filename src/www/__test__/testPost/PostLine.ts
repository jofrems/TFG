import chalk from "chalk";

export class PostLine {
  #postId: string;
  #testId: string;
  #content: string;
  #lineNumber: number;

  constructor(
    postId: string,
    testId: string,
    content: string,
    lineNumber: number
  ) {
    this.#postId = postId;
    this.#testId = testId;
    this.#content = content;
    this.#lineNumber = lineNumber;
  }

  match(re: RegExp): ReturnType<string["match"]> {
    return this.#content.match(re);
  }

  public toPrettyString(
    colorizeContent: (content: string) => string = chalk.blue,
    colorizeLocation: (content: string) => string = chalk.gray
  ): string {
    return (
      colorizeLocation(`${this.#postId}.md:${this.#lineNumber}`) +
      " " +
      colorizeContent(this.#content)
    );
  }
}
