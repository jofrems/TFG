import { ComponentOwnActionTypes, ComponentOwnState } from "./types";

export function componentOwnReducer(
  state: ComponentOwnState = null,
  action: ComponentOwnActionTypes
) {
  switch (action.type) {
    default:
      return state;
  }
}
