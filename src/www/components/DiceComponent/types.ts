export type ComponentDiceState = null;

export const ROLL = "componentDice/ROLL";
export interface RollAction {
  type: typeof ROLL;
  entityId: string;
  playerName: string;
}

export type ComponentDiceActionTypes = RollAction;
