import { screen, waitForElementToBeRemoved } from "@testing-library/dom";
import { PostLineStep } from "./PostLineStep";
import { snapshotService } from "./SnapshotService";

export const systemPostLineSteps = [
  new PostLineStep(/^[^\s]/, () => {}),
  new PostLineStep(/^\s*$/, () => {}),
  new PostLineStep(/SNAPSHOT status=/, async () => {
    await snapshotService.nextSnapshot();
    expect(await screen.findByTestId("loading")).toBeInTheDocument();
    await waitForElementToBeRemoved(() => screen.queryByTestId("loading"));
  }),
].flat(Infinity);
