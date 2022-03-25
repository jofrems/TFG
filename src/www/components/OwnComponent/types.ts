export type ComponentOwnState = null;

export const OWN = "componentOwn/OWN";
export interface OwnAction {
  type: typeof OWN;
  entityId: string;
  playerName: string;
}

export type ComponentOwnActionTypes = OwnAction;
