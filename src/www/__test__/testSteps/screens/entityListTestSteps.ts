import { PostLineStep, step } from "../../testPost";
import { screen } from "@testing-library/dom";
import userEvent from "@testing-library/user-event";

export const entityListTestSteps: PostLineStep[] = [
  step(
    /"([^"]+)" should have (\d+) ([a-z]+)/,
    (line, [, owner, count, type]) => {
      expectToHaveEntitiesByContentsAndCount([owner, type], +count);
    }
  ),
  step(
    /There should be (\d+) "([^"]+)" ([a-z]+)/,
    (line, [, count, owner, type]) => {
      expectToHaveEntitiesByContentsAndCount([owner, type], +count);
    }
  ),
  step(
    /"([^"]+)" should have the "([^"]+)" ([a-z]+)/,
    (line, [, owner, name, type]) => {
      expect(
        getEntityListItemByContents([owner, type, name])
      ).toBeInTheDocument();
    }
  ),
  step(
    /There should be the "([^"]+)" "([^"]+)" ([a-z]+)/,
    (line, [, owner, name, type]) => {
      expect(
        getEntityListItemByContents([owner, type, name])
      ).toBeInTheDocument();
    }
  ),
  step(
    /Click on the "([^"]+)" (owned )?([a-z]+) "([^"]+)"/,
    (line, [, owner, , type, name]) => {
      var item = getEntityListItemByContents([owner, type, name]);
      userEvent.click(item);
    }
  ),
];

function expectToHaveEntitiesByContentsAndCount(
  contents: string[],
  count: number
) {
  let entities =
    count > 0
      ? getAllEntityListItemByContents(contents)
      : queryAllEntityListItemByContents(contents);

  expect(entities).toHaveLength(count);
}

function queryAllEntityListItems(): HTMLElement[] {
  return screen.queryAllByRole("listitem");
}

function queryAllEntityListItemByContents(contents: string[]): HTMLElement[] {
  const allEntities = queryAllEntityListItems();

  const result = allEntities.filter((entity) =>
    contents.every((match) => entity.textContent!.includes(match))
  );

  return result;
}

function getAllEntityListItemByContents(contents: string[]): HTMLElement[] {
  const allEntities = queryAllEntityListItemByContents(contents);

  const result = allEntities.filter((entity) =>
    contents.every((match) => entity.textContent!.includes(match))
  );

  if (result.length === 0)
    throw new Error(
      `Could not find any entity in the games with "${contents.join(
        '"", "'
      )}".\n` +
        `Available items are:\n- ` +
        queryAllEntityListItems()
          .map((g) => g.textContent)
          .join("\n- ")
    );

  return result;
}

function getEntityListItemByContents(contents: string[]): HTMLElement {
  const allEntities = getAllEntityListItemByContents(contents);
  if (allEntities.length > 1)
    throw new Error(
      `Found more than one entity in the games with "${contents.join(
        '"", "'
      )}".\n` +
        `Available items are:\n- ` +
        queryAllEntityListItems()
          .map((g) => g.textContent)
          .join("\n- ")
    );

  return allEntities[0];
}
