import { PostLine } from "./PostLine";

type StepRunFn = (
  postLine: PostLine,
  match: Exclude<ReturnType<string["match"]>, null>
) => void | Promise<void>;

export class PostLineStep {
  #matchRe: RegExp;
  #run: StepRunFn;

  constructor(matchRe: RegExp, run: StepRunFn) {
    this.#matchRe = matchRe;
    this.#run = run;
  }

  matches(postLine: PostLine): boolean {
    return postLine.match(this.#matchRe) != null;
  }

  run(postLine: PostLine) {
    var match = postLine.match(this.#matchRe);
    return this.#run(postLine, match!);
  }

  toPrettyString(): any {
    return `/${this.#matchRe.source}/ ${this.#run.toString()}`;
  }
}

export const step = (matchRe: RegExp, run: StepRunFn): PostLineStep => {
  return new PostLineStep(matchRe, run);
};
