import { NameChangedAction, NAME_CHANGED } from "./types";

export function nameChanged(
  entityId: string,
  newName: string
): NameChangedAction {
  return {
    type: NAME_CHANGED,
    entityId,
    form: {
      newName,
    },
  };
}
