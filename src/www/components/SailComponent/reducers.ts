import { ComponentSailActionTypes, ComponentSailState } from "./types";

export function componentSailReducer(
  state: ComponentSailState = null,
  action: ComponentSailActionTypes
) {
  switch (action.type) {
    default:
      return state;
  }
}
