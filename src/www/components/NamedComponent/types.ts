export type ComponentNamedState = null;

export const NAME_CHANGED = "componentNamed/NAME_CHANGED";
export interface NameChangedAction {
  type: typeof NAME_CHANGED;
  entityId: string;
  form: {
    newName: string;
  };
}

export type ComponentNamedActionTypes = NameChangedAction;
