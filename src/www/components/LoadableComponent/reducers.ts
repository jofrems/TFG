import { ComponentLoadableActionTypes, ComponentLoadableState } from "./types";

export function componentLoadableReducer(
  state: ComponentLoadableState = null,
  action: ComponentLoadableActionTypes
) {
  switch (action.type) {
    default:
      return state;
  }
}
