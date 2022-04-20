import { RollAction, ROLL } from "./types";

export function roll(
  entityId: string,
  playerName: string
): RollAction {
  return {
    type: ROLL,
    entityId,
    playerName,
  };
}
