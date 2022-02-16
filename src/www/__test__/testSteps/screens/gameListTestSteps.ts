import { PostLineStep, step } from "../../testPost";
import { screen } from "@testing-library/dom";
import userEvent from "@testing-library/user-event";
import { getByRole } from "@testing-library/react";

export const gameListTestSteps: PostLineStep[] = [
  step(/Go to my created games/, () => {
    const link = screen.getByRole("link", { name: /My created games/ });
    userEvent.click(link);
  }),
  step(/You should be at the list games screen/, () => {
    expect(
      screen.getByRole("heading", { name: "Games List!" })
    ).toBeInTheDocument();
  }),
  step(/There should be (\d)+ game/, (line, match) => {
    const count = +match[1];

    expect(queryAllGameListItems()).toHaveLength(count);
  }),
  step(/Games list should be empty/, () => {
    expect(queryAllGameListItems()).toHaveLength(0);
  }),
  step(/You should see the game "([^"]+)"/, (line, match) => {
    const gameName = match[1];

    const list = queryAllGameListItems();
    const game = list.find((item) => item.textContent?.includes(gameName));
    expect(game).toBeInTheDocument();
  }),
  step(/You should see no game "([^"]+)"/, (line, match) => {
    const gameName = match[1];

    const list = queryAllGameListItems();
    const games = list.filter((item) => item.textContent?.includes(gameName));
    expect(games).toHaveLength(0);
  }),
  step(/Return from the list games/, () => {
    const button = screen.getByRole("button", { name: "Cancel" });
    userEvent.click(button);
  }),
  step(/Join on the game "([^"]+)" created by "([^"]+)"/, (line, match) => {
    const [, gameName, creatorName] = match;
    const item = getGameListItemByNameAndCreator(gameName, creatorName);
    const button = getByRole(item, "button", { name: "Join" });
    userEvent.click(button);
  }),
  step(
    /You should have joined the game "([^"]+)" created by "([^"]+)"/,
    (line, [, gameName, creatorName]) => {
      const item = getGameListItemByNameAndCreator(gameName, creatorName);
      expect(item).toHaveTextContent("joined");
    }
  ),
  step(
    /You should have not joined the game "([^"]+)" created by "([^"]+)"/,
    (line, [, gameName, creatorName]) => {
      const item = getGameListItemByNameAndCreator(gameName, creatorName);
      expect(item).not.toHaveTextContent("joined");
    }
  ),
  step(/Play on the game "([^"]+)" created by "([^"]+)"/, (line, match) => {
    const [, gameName, creatorName] = match;
    const item = getGameListItemByNameAndCreator(gameName, creatorName);
    const button = getByRole(item, "button", { name: "Play" });
    userEvent.click(button);
  }),
];

function getGameListItemByNameAndCreator(
  gameName: string,
  creatorName: string
): HTMLElement {
  const allGames = getAllGameListItems();

  const game = allGames
    .filter((item) => item.textContent?.includes(gameName))
    .find((item) => item.textContent?.includes(creatorName));

  if (!game)
    throw new Error(
      `Could not find any game in the games list with name "${gameName}" and creator "${creatorName}".\n` +
        `Available items are:\n- ` +
        allGames.map((g) => g.textContent).join("\n- ")
    );

  return game;
}

function queryAllGameListItems(): HTMLElement[] {
  return screen.queryAllByRole("listitem");
}

function getAllGameListItems(): HTMLElement[] {
  return screen.getAllByRole("listitem");
}
