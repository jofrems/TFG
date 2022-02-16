import { AppState } from "www/store";
import { MessageState } from "./types";

export function getMessage(state: AppState): MessageState {
  return state.widgetMessage;
}
