import {
  SCREEN_PUSHED,
  SCREEN_POPPED,
  ScreenStackActionTypes,
  ScreenStackState,
} from "./types";

const initialState: ScreenStackState = [{ name: "welcome" }];

export function widgetScreenStackReducer(
  state = initialState,
  action: ScreenStackActionTypes
) {
  switch (action.type) {
    case SCREEN_PUSHED:
      return [...state, action.screen];
    case SCREEN_POPPED:
      return state.slice(0, state.length - 1);
    default:
      return state;
  }
}
