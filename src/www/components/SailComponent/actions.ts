import {
  HaltOrderedAction,
  HALT_ORDERED,
  SailOrderedAction,
  SAIL_ORDERED,
} from "./types";

export function sailOrdered(
  entityId: string,
  destinationLocation: number
): SailOrderedAction {
  return {
    type: SAIL_ORDERED,
    entityId,
    form: {
      destinationLocation: +destinationLocation,
    },
  };
}
export function haltOrdered(entityId: string): HaltOrderedAction {
  return {
    type: HALT_ORDERED,
    entityId,
  };
}
