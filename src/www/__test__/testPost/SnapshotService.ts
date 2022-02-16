import { diff } from "jest-diff";
import { isEqual } from "underscore";
import { rest } from "msw";
import { setupServer } from "msw/node";
import { readResource } from "./readResource";
import chalk from "chalk";

type Snapshot = {
  method: string;
  url: string;
  requestBody: string;
  responseStatus: number;
  responseBody: string;
};

type TestSnapshots = Snapshot[];

type FileSnapshots = {
  [testName: string]: TestSnapshots;
};

class SnapshotService {
  #snapshots: TestSnapshots = [];
  #snapshotIndex: number = 0;
  #isSnapshotConsumed: boolean = false;
  #lastError: any;
  #snapshotId: string = "unread";

  read(postId: string, testId: string) {
    const content = readResource("snapshots", `${postId}.json`);
    const fileSnapshots: FileSnapshots = JSON.parse(content);
    this.#snapshots = fileSnapshots?.[testId];
    this.#snapshotIndex = 0;
    this.#isSnapshotConsumed = false;
    this.#snapshotId = `${postId}.md#${testId}`;
    if (!this.#snapshots)
      throw new Error(
        `Cannot read snapshots from "target/classes/snapshots/${postId}.json" and testId "${testId}".\n` +
          `- Make sure that the backend tests have run before and both versions match`
      );
  }

  respond(method: string, url: string, body: any) {
    const id = `${this.#snapshotId}:${this.#snapshotIndex}`;

    try {
      if (this.#isSnapshotConsumed)
        throw new Error(
          `Missing SNAPSHOT status=XXX Step ${id}.\n` +
            `The last snapshot was:\n` +
            `${prettySnapshot(this.#snapshots[this.#snapshotIndex])}\n` +
            `But now the frontend is creating another non-expected API request that is:\n` +
            `- method: ${method}\n` +
            `- url   : ${url}\n` +
            `- body  : ${JSON.stringify(body)}\n`
        );

      const snapshot = this.#snapshots[this.#snapshotIndex];
      if (!snapshot)
        throw new Error(
          `Unexpected API requests found${id}.\n` +
            ` There are no more snapshots but a request from the frontend to the backend was done with the following data:\n` +
            `- method: ${method}\n` +
            `- url   : ${url}\n` +
            `- body  : ${JSON.stringify(body)}\n`
        );

      const expectedRequest = {
        method: snapshot.method,
        url: snapshot.url,
        body: JSON.parse(snapshot.requestBody),
      };
      const actualRequest = { method, url, body };

      if (!isEqual(actualRequest, expectedRequest))
        throw new Error(
          `Wrong API request found ${id}.\n` +
            `The changes are: \n` +
            `${diff(expectedRequest, actualRequest)}\n\n` +
            chalk.green(`The expected request was:\n`) +
            `${prettySnapshot(snapshot)}\n` +
            chalk.red(`The actual request was:\n`) +
            `- method: ${method}\n` +
            `- url   : ${url}\n` +
            `- body  : ${JSON.stringify(body, null, 2)}\n`
        );

      this.#isSnapshotConsumed = true;
      return [snapshot.responseStatus, JSON.parse(snapshot.responseBody)];
    } catch (error) {
      this.#failedWithError(error);
      return [500, { message: "" + error, isError: true }];
    }
  }

  getToken(): string | null {
    const snapshot = this.#snapshots[this.#snapshotIndex];
    const token = new URL("https:/" + snapshot.url).searchParams.get("token");
    return token;
  }

  async nextSnapshot() {
    const startTs = Date.now();
    while (!this.#isSnapshotConsumed) {
      await new Promise((r) => setTimeout(r, 50));
      const currentTs = Date.now();
      if (currentTs - startTs > 1000)
        throw new Error(
          `Waiting too long for the frontend make an API request for the frontend.\n` +
            chalk.green(
              `\nThe test snapshot was expecting the following request: \n`
            ) +
            prettySnapshot(this.#snapshots[this.#snapshotIndex]) +
            chalk.red(
              `\nBut it seems that the frontend never made that API call.\n`
            ) +
            `- Do you have corresponding middleware with the fetch?\n` +
            `- Is the middleware present and added to the middleware list?\n` +
            `- Did was the corresponding action dispatched, so the middleware can handle it?\n`
        );
    }

    this.#snapshotIndex++;
    this.#isSnapshotConsumed = false;
  }

  throwIfFailed() {
    if (this.#lastError) {
      const error = this.#lastError;
      this.#lastError = null;
      throw error;
    }
  }

  #failedWithError = (error: any) => {
    this.#lastError = error;
  };
}

export const snapshotService = new SnapshotService();

const server = setupServer(
  rest.get("/*", (req, res, ctx) => {
    const [status, response] = snapshotService.respond(
      "GET",
      req.url.pathname + req.url.search,
      null
    );
    return res(ctx.status(status), ctx.json(response));
  }),
  rest.post("/*", (req, res, ctx) => {
    const [status, response] = snapshotService.respond(
      "POST",
      req.url.pathname + req.url.search,
      req.body
    );
    return res(ctx.status(status), ctx.json(response));
  })
);

beforeAll(() => server.listen());
afterEach(() => server.resetHandlers());
afterAll(() => server.close());

function prettySnapshot(snapshot: Snapshot) {
  return (
    `- method: ${snapshot.method}\n` +
    `- url   : ${snapshot.url}\n` +
    `- body  : ${snapshot.requestBody}\n`
  );
}
