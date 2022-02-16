import {
  MESSAGE_DISMISSED,
  MESSAGE_SHOWN,
  MessageState,
  MessageActionTypes,
} from "./types";

export function widgetMessageReducer(
  state: MessageState = null,
  action: MessageActionTypes
) {
  switch (action.type) {
    case MESSAGE_SHOWN:
      return action.message;
    case MESSAGE_DISMISSED:
      return null;
    default:
      return state;
  }
}
