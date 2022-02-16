import { ComponentNamedActionTypes, ComponentNamedState } from "./types";

export function componentNamedReducer(
  state: ComponentNamedState = null,
  action: ComponentNamedActionTypes
) {
  switch (action.type) {
    default:
      return state;
  }
}
