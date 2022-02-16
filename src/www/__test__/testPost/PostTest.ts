import { Chalk } from "chalk";
import { PostLine } from "./PostLine";

export class PostTest {
  #postId: string;
  #title: string;
  #lines: PostLine[] = [];
  #testId: string;

  constructor(postId: string, title: string) {
    this.#postId = postId;
    this.#title = title;
    this.#testId = title
      .toLowerCase()
      .replace(/[^a-z]+/g, "-")
      .replace(/^-/, "")
      .replace(/-$/, "");
  }

  addLine(line: string, lineNumber: number) {
    this.#lines.push(
      new PostLine(this.#postId, this.#testId, line, lineNumber)
    );
  }

  async forEachLine(fn: (line: PostLine) => Promise<void>) {
    for (let i = 0; i < this.#lines.length; i++) await fn(this.#lines[i]);
  }

  getTestId(): string {
    return this.#testId;
  }

  toPrettyName(): string {
    return this.#lines[0].toPrettyString(
      (s) => s,
      (s) => s
    );
  }

  toPrettyString(): any {
    return `${this.#title}\n${this.#lines
      .map((l) => l.toPrettyString())
      .join("\n")}`;
  }

  toPrettyStringPreviousLines(stopLine: PostLine, colorize: Chalk): string[] {
    let result = [];
    for (
      let i = 0;
      i < this.#lines.length && this.#lines[i] !== stopLine;
      i++
    ) {
      result.push(this.#lines[i].toPrettyString(colorize));
    }
    return result;
  }

  toPrettyStringNextLines(
    stopLine: PostLine,
    colorizeLine: Chalk,
    colorizePosition: Chalk
  ) {
    let result = [];
    let index = 0;
    while (index < this.#lines.length && this.#lines[index] !== stopLine)
      index += 1;
    index += 1;
    for (; index < this.#lines.length; index++) {
      result.push(
        this.#lines[index].toPrettyString(colorizeLine, colorizePosition)
      );
    }
    return result;
  }
}
