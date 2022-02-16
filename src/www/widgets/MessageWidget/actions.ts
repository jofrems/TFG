import {
  MessageShownAction,
  MessageDismissedAction,
  MESSAGE_SHOWN,
  MESSAGE_DISMISSED,
} from "./types";

export function messageShown(
  text: string,
  isError: boolean = false
): MessageShownAction {
  return {
    type: MESSAGE_SHOWN,
    message: {
      text,
      isError,
    },
  };
}

export function messageDismissed(): MessageDismissedAction {
  return {
    type: MESSAGE_DISMISSED,
  };
}
