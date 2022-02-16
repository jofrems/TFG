import {
  LoadingActionTypes,
  LoadingState,
  LOADING_FINISHED,
  LOADING_STARTED,
} from "./types";

export function widgetLoadingReducer(
  state: LoadingState = 0,
  action: LoadingActionTypes
) {
  switch (action.type) {
    case LOADING_STARTED:
      return state + 1;
    case LOADING_FINISHED:
      return state - 1;
    default:
      return state;
  }
}
