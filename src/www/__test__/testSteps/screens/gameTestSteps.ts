import { PostLineStep, step } from "../../testPost";
import { testDispatch } from "../../testStore";
import { snapshotService } from "../../testPost/SnapshotService";
import { screen } from "@testing-library/dom";
import { gamePlayed, gameRefreshed } from "www/store/game/actions";
import { playerReplaced } from "www/store/player/actions";
import userEvent from "@testing-library/user-event";

export const gameTestSteps: PostLineStep[] = [
  step(/You should be at the game screen/, () => {
    expect(screen.getByRole("heading", { name: "Game!" })).toBeInTheDocument();
  }),
  step(
    /Playing game should be "([^"]+)" created by "([^"]+)"/,
    (title, [, gameName, creatorName]) => {
      const header = screen.getByTestId("game-header");
      expect(header).toHaveTextContent(`${gameName} created by ${creatorName}`);
    }
  ),
  step(
    /"([^"]+)" is playing their game "([^"]+)"/,
    (title, [, creatorName, gameName]) => {
      const token = snapshotService.getToken()!;
      testDispatch(playerReplaced(creatorName, token));
      testDispatch(gamePlayed(gameName, creatorName));
    }
  ),
  step(
    /there is "([^"]+)" playing their game "([^"]+)"/,
    (title, [, creatorName, gameName]) => {
      const token = snapshotService.getToken()!;
      testDispatch(playerReplaced(creatorName, token));
      testDispatch(gamePlayed(gameName, creatorName));
    }
  ),
  step(
    /"([^"]+)" should have (\d+) ([a-z]+)"/,
    (title, [, creatorName, gameName]) => {
      const token = snapshotService.getToken()!;
      testDispatch(playerReplaced(creatorName, token));
      testDispatch(gamePlayed(gameName, creatorName));
    }
  ),
  step(/Game round should be (\d+)/, (title, [, round]) => {
    const header = screen.getByTestId("game-header");
    expect(header).toHaveTextContent(`round ${round}`);
  }),
  step(/End the round/, endRound),
  step(/Skip \d+ rounds/, endRound),
  step(/Refresh the game/, () => {
    const button = screen.getByRole("button", { name: "Refresh" });
    userEvent.click(button);
  }),
];

function endRound() {
  const button = screen.getByRole("button", { name: "End Round" });
  userEvent.click(button);
}
