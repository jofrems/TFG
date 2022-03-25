import { OwnAction, OWN } from "./types";

export function own(
  entityId: string,
  playerName: string
): OwnAction {
  return {
    type: OWN,
    entityId,
    playerName,
  };
}
