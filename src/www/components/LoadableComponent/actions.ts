import {
  LoadOrderedAction,
  LOAD_ORDERED,
  UnloadOrderedAction,
  UNLOAD_ORDERED,
} from "./types";

export function loadOrdered(
  entityId: string,
  sourceEntityId: string,
  loadUnloadAmount: number
): LoadOrderedAction {
  return {
    type: LOAD_ORDERED,
    entityId,
    form: {
      sourceEntityId,
      loadUnloadAmount: +loadUnloadAmount,
    },
  };
}

export function unloadOrdered(
  entityId: string,
  sourceEntityId: string,
  loadUnloadAmount: number
): UnloadOrderedAction {
  return {
    type: UNLOAD_ORDERED,
    entityId,
    form: {
      sourceEntityId,
      loadUnloadAmount: +loadUnloadAmount,
    },
  };
}
