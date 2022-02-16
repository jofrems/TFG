import { AppState } from "www/store";

export function isLoading(state: AppState) {
  return state.widgetLoading > 0;
}
