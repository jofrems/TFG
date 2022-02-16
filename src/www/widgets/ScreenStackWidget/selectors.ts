import { AppState } from "www/store";

export function getCurrentScreenName(state: AppState) {
  return state.widgetScreenStack[state.widgetScreenStack.length - 1]?.name;
}
export function getCurrentScreenId(state: AppState) {
  return state.widgetScreenStack[state.widgetScreenStack.length - 1]?.id;
}
