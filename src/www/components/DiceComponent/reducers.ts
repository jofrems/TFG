import { ComponentDiceActionTypes, ComponentDiceState } from "./types";

export function componentDiceReducer(
  state: ComponentDiceState = null,
  action: ComponentDiceActionTypes
) {
  switch (action.type) {
    default:
      return state;
  }
}
