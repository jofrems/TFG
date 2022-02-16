export type ComponentSailState = null;

export const SAIL_ORDERED = "componentSail/SAIL_ORDERED";
export interface SailOrderedAction {
  type: typeof SAIL_ORDERED;
  entityId: string;
  form: {
    destinationLocation: number;
  };
}

export const HALT_ORDERED = "componentSail/HALT_ORDERED";
export interface HaltOrderedAction {
  type: typeof HALT_ORDERED;
  entityId: string;
}

export type ComponentSailActionTypes = SailOrderedAction | HaltOrderedAction;
